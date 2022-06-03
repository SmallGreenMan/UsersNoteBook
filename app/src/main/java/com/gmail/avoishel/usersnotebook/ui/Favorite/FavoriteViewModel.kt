package com.gmail.avoishel.usersnotebook.ui.Favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel  @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

//    private val _userList = Channel<UsersPageResponse>()
//    val userList = _userList.receiveAsFlow()
//
//    private val _userList = MutableLiveData<Resource<UsersPageResponse>>()
//    val userList: MutableLiveData<Resource<UsersPageResponse>> = _userList

    fun getSavedUsers() = userRepository.getSavedUsers()

    fun deleteUser(user: UserModel) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
}