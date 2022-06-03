package com.gmail.avoishel.usersnotebook.ui.userInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.data.models.UserModel
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel  @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    fun saveUser(user: UserModel) = viewModelScope.launch {
        userRepository.insertUser(user)
    }
}