package com.gmail.avoishel.usersnotebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gmail.avoishel.usersnotebook.databinding.ActivityMainBinding
import com.gmail.avoishel.usersnotebook.repository.UserRepository

class MainActivity : AppCompatActivity() {

    lateinit var userListViewModel: UserListViewModel


    private lateinit var binding: ActivityMainBinding
    //private lateinit var recyclerAdapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val userRepository = UserRepository()
//        val userListProviderFactory = UserListViewModelProviderFactory(userRepository)
//        userListViewModel = ViewModelProvider(this, userListProviderFactory).get(UserListViewModel::class.java)

    }

//    private fun initRecyclerView(){
//        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerAdapter = UsersListAdapter(this)
//        binding.usersListRecyclerView.adapter = recyclerAdapter
//    }
//
//    private fun initViewModel(){
//        val viewModel : MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        viewModel.getLiveDataObserver().observe(this, Observer {
//            if (it != null){
//                recyclerAdapter.setUserList(it.data)
//                recyclerAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(this,"Error in getting users data", Toast.LENGTH_LONG).show()
//            }
//        })
//        viewModel.makeApiCall()
//    }
}