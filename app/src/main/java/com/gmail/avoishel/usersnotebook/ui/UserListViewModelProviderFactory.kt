package com.gmail.avoishel.usersnotebook.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.ui.UserList.UserListViewModel
import javax.inject.Inject


class UserListViewModelProviderFactory @Inject constructor(
    private val userRepository : UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(userRepository) as T
    }
}