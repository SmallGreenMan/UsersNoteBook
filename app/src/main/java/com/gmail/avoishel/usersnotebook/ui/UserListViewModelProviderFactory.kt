package com.gmail.avoishel.usersnotebook.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.avoishel.usersnotebook.repository.UserRepository

class UserListViewModelProviderFactory(
    val userRepositiry : UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(userRepositiry) as T
    }
}