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

    fun getSavedUsers() = userRepository.getSavedUsers()

    fun saveUser(user: UserModel) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun findUserByIdInDb(id: Int) = userRepository.findUserById(id)

    fun deleteUser(user: UserModel) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
}