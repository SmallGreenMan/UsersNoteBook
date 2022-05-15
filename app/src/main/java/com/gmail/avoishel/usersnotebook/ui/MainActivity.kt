package com.gmail.avoishel.usersnotebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.ActivityMainBinding
import com.gmail.avoishel.usersnotebook.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initRecyclerView()
//        initViewModel()
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