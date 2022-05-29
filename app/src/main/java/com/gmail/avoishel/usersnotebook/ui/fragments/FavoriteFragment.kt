package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.FavoriteFragmentBinding
import com.gmail.avoishel.usersnotebook.ui.UserListViewModel
import com.gmail.avoishel.usersnotebook.utils.Picasso.PicassoUtil
import javax.inject.Inject

class FavoriteFragment: Fragment() {

    @Inject
    lateinit var picassoUtil: PicassoUtil

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    val userListViewModel: UserListViewModel by lazy {
        ViewModelProvider(this).get(UserListViewModel::class.java)
    }

    lateinit var userListAdapter: UsersListAdapter

    //private val args: FavoriteFragmentBinding by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        userListViewModel.getSavedUsers().observe(viewLifecycleOwner, Observer { users ->
            userListAdapter.differ.submitList(users)
        })
    }

    private fun setupRecyclerView() {
        userListAdapter = UsersListAdapter()
        binding.rvUsersList.apply {
            adapter = userListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            //addOnScrollListener(this@FavoriteFragment.s)
        }
    }
}