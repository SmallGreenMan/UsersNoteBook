package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.UserListFragmentBinding
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.ui.MainActivity
import com.gmail.avoishel.usersnotebook.ui.UserListViewModel
import com.gmail.avoishel.usersnotebook.ui.UserListViewModelProviderFactory
import com.gmail.avoishel.usersnotebook.utility.Resource

class UserListFragment: Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var userListViewModel: UserListViewModel
    lateinit var userListAdapter: UsersListAdapter

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

         //userListViewModel = (activity as MainActivity).viewModel

        //userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        val userRepository = UserRepository()
        val userListProviderFactory = UserListViewModelProviderFactory(userRepository)
        userListViewModel = ViewModelProvider(this, userListProviderFactory).get(UserListViewModel::class.java)


        setupRecyclerView()

        userListViewModel.userList.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { userListResponse ->
                        userListAdapter.differ.submitList(userListResponse.data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(this.context, "Error in getting data from server", Toast.LENGTH_LONG).show()
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
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        userListAdapter = UsersListAdapter()
        binding.rvUsersList.apply{
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}