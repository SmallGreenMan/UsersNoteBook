package com.gmail.avoishel.usersnotebook.ui.Favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.adapter.UsersListAdapter
import com.gmail.avoishel.usersnotebook.databinding.FavoriteFragmentBinding
import com.gmail.avoishel.usersnotebook.utils.PicassoUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment: Fragment() {

    @Inject
    lateinit var picassoUtil: PicassoUtil

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!


    private val favoriteViewModel: FavoriteViewModel by viewModels()

    lateinit var userListAdapter: UsersListAdapter

    private val TAG = "FavoriteFragment"

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

        favoriteViewModel.getSavedUsers().observe(viewLifecycleOwner) {
            userListAdapter.differ.submitList(it)
        }

        userListAdapter.setOnItemClickListener {
            Log.i(TAG, "-----> Click on rv-favorite item !!!")
            Toast.makeText(context, "${it.first_name} ${it.last_name}", Toast.LENGTH_LONG).show()
            val bundle = Bundle().apply {
                putSerializable("user", it)
            }
            findNavController().navigate(
                R.id.action_favoriteFragment_to_userInfoFragment,
                bundle
            )
        }

        userListAdapter.setFavoriteClickListener { user ->
            Log.i(TAG, "-----> Click on favorite item !!! userId: ${user.id}, fav: ${user.favorite}")
           if (user.favorite)
                favoriteViewModel.deleteUser(user)
            else {
                user.favorite = true
                favoriteViewModel.saveUser(user)
            }
        }
    }

    private fun setupRecyclerView() {
        userListAdapter = UsersListAdapter()
        binding.rvUsersList.apply {
            adapter = userListAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}