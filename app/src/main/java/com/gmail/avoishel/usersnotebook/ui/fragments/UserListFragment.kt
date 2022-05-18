package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.UserListFragmentBinding
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.ui.MainActivity
import com.gmail.avoishel.usersnotebook.ui.UserListViewModel
import com.gmail.avoishel.usersnotebook.ui.UserListViewModelProviderFactory
import com.gmail.avoishel.usersnotebook.utility.Constants.Companion.QUERY_PAGE_SIZE
import com.gmail.avoishel.usersnotebook.utility.Resource

class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var userListViewModel: UserListViewModel
    lateinit var userListAdapter: UsersListAdapter

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

    val TAG = "UserListFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepository = UserRepository()
        val userListProviderFactory = UserListViewModelProviderFactory(userRepository)
        userListViewModel =
            ViewModelProvider(this, userListProviderFactory).get(UserListViewModel::class.java)

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
                        userListAdapter.differ.submitList(userListResponse.data.toList())
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