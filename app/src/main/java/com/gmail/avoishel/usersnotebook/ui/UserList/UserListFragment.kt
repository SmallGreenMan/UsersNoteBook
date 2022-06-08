package com.gmail.avoishel.usersnotebook.ui.UserList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.UserListFragmentBinding
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.gmail.avoishel.usersnotebook.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private val userListViewModel: UserListViewModel by viewModels()

    lateinit var userListAdapter: UsersListAdapter

    lateinit var userListInDb: List<UserModel>

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
            Log.i(
                "TAG",
                "-----> onScrollStateChanged! stateIs = $newState, isLoading = $isLoading, isLastPage = $isLastPage, isScrolling = $isScrolling"
            )
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager =
                recyclerView.layoutManager as GridLayoutManager //LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNoLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNoLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling

            Log.i(
                "TAG",
                "-----> ScrollEnd! isLoading = $isLoading, isLastPage = $isLastPage, isScrolling = $isScrolling, " +
                        "isNotLoadingAndNoLastPage = $isNotLoadingAndNoLastPage, isAtLastItem = $isAtLastItem, shouldPaginate = $shouldPaginate"
            )

            if (shouldPaginate) {
                userListViewModel.getUserList()
                isScrolling = false
            } else {
                binding.rvUsersList.setPadding(0, 0, 0, 0) // !!!!!!!!!!!!!!!!
            }
        }
    }

    private val TAG = "UserListFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        userListAdapter.setOnItemClickListener {
            Log.i(TAG, "-----> Click on rv item !!!")
            Toast.makeText(context, "${it.first_name} ${it.last_name}", Toast.LENGTH_LONG).show()
            val bundle = Bundle().apply {
                putSerializable("user", it)
            }
            findNavController().navigate(
                R.id.action_userListFragment_to_userInfoFragment,
                bundle
            )
        }

        userListViewModel.userList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { userListResponse ->
                        val checkedUserList = chekUserInDb(userListResponse.data.toList())
                        //userListAdapter.differ.submitList(checkedUserList)
                        val totalPages = userListResponse.total_pages
                        isLastPage = userListViewModel.userListPage - 1 == totalPages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(
                            this.context,
                            "Error in getting data from server",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e(TAG, "----------> Error in getting data from server: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        userListViewModel.userInDbLiveData.observe(viewLifecycleOwner, Observer { checkedUserList ->
            userListAdapter.differ.submitList(checkedUserList)
        })
    }

    private fun chekUserInDb(userList: List<UserModel>): List<UserModel> {

//        userList.forEach{ user ->
//            userListViewModel.findUserByIdInDb(user.id!!).observe(viewLifecycleOwner)  { user ->
//                Log.i(TAG, " --- > SingleUser 3333 db: ${user.joinToString()}")
//                if (user.isEmpty()) {
//                    Log.i(TAG, " --- > SingleUser 4444 empty: ${user.joinToString()}")
//                } else {
//                    Log.i(TAG, " --- > SingleUser 5555 not empty: ${user.joinToString()}")
//                }
//            }
//        }

        userListViewModel.getSavedUsers().observe(viewLifecycleOwner) {
            userListInDb = it

            for (key in (0..userList.size-1)){
                if (userExistInList(userList[key].id!!,it)){
                //if (userListInDb.contains(userList[key])) {
                    userList[key].favorite = true
                }
                Log.i(TAG, " --- > SingleUser 3333 key: ${key}  condition: ${userExistInList(userList[key].id!!,it)}")
            }

            Log.i(TAG, " --- > UsersList 1111 after comparison with db: ${userList.joinToString()}")
            userListViewModel.userInDbLiveData.postValue(userList)
        }

        Log.i(TAG, " --- > UsersList 2222 after comparison with db: ${userList.joinToString()}")
        return userList
    }

    private fun userExistInList(id: Int, users: List<UserModel>): Boolean {
        users.forEach{
            if (it.id == id) return true
        }
        return false
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }


    private fun setupRecyclerView() {
        userListAdapter = UsersListAdapter()
        binding.rvUsersList.apply {
            adapter = userListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(this@UserListFragment.scrollListener)
        }
    }
}