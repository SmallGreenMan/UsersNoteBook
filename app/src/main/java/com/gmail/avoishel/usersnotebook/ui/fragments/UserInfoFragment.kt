package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.databinding.UserInfoFragmentBinding
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.ui.UserListViewModel
import com.gmail.avoishel.usersnotebook.utils.Picasso.PicassoUtil
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserInfoFragment: Fragment(R.layout.user_info_fragment) {

    @Inject
    lateinit var picassoUtil: PicassoUtil

    val userListViewModel: UserListViewModel by lazy {
        ViewModelProvider(this).get(UserListViewModel::class.java)
    }

    private var _binding: UserInfoFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserInfoFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = args.user

        showData(user)

        binding.fab.setOnClickListener {
            userListViewModel.saveUser(user)
            Snackbar.make(view, "User saved in favorites", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showData(user: UserModel){
        binding.nameTextView.text = "${user.first_name} ${user.last_name}"
        binding.emailTextView.text = user.email

        picassoUtil.loadImage(user.avatar!!, binding.headerImageView)
    }
}