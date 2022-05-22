package com.gmail.avoishel.usersnotebook.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.databinding.FavoriteFragmentBinding
import com.gmail.avoishel.usersnotebook.utils.Picasso.PicassoUtil
import javax.inject.Inject

class FavoriteFragment: Fragment() {

    @Inject
    lateinit var picassoUtil: PicassoUtil

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    //private val args: FavoriteFragmentBinding by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }
}