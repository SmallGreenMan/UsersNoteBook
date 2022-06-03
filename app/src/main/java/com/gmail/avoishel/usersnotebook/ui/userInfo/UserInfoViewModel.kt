package com.gmail.avoishel.usersnotebook.ui.userInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel  @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

//    private val _userEventChannel = Channel<UserEvent>()
//    val userEvent = _userEventChannel.receiveAsFlow()

    fun saveUser(user: UserModel) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

//    fun getSavedUsers() = userRepository.getSavedUsers()
//
//    fun deleteUser(user: UserModel) = viewModelScope.launch {
//        userRepository.deleteUser(user)
//    }

//    sealed class UserEvent{
//        data class ShowUserSavedMessage(val message: String): UserEvent()
//    }
}