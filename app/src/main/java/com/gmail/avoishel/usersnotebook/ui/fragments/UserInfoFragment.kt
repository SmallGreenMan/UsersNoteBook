package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.databinding.UserInfoFragmentBinding
import com.gmail.avoishel.usersnotebook.databinding.UserListFragmentBinding
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.squareup.picasso.Picasso

class UserInfoFragment: Fragment(R.layout.user_info_fragment) {

    private var _binding: UserInfoFragmentBinding? = null
    private val binding get() = _binding!!

    val args: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
    }

    private fun showData(user: UserModel){
        binding.nameTextView.text = "${user.first_name} ${user.last_name}"
        binding.emailTextView.text = user.email

        Picasso.get()
            .load(user.avatar)
            //.placeholder(R.drawable.ic_placeholder_foreground)
            //.error(R.drawable.ic_placeholder_error_foreground)
            .into(binding.headerImageView)
    }
}