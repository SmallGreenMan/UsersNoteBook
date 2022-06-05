package com.gmail.avoishel.usersnotebook.ui.UserList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.data.retrofit.SimpleResponse
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel  @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _userList = MutableLiveData<Resource<UsersPageResponse>>()
    val userList: MutableLiveData<Resource<UsersPageResponse>> = _userList

    var userListPage = 1
    var userListResponse: UsersPageResponse? = null

    init {
        getUserList()
    }

    fun getUserList() = viewModelScope.launch {
        _userList.postValue(Resource.Loading())
        Log.i("UserListViewModel", "-----> try to get userList page = $userListPage")
        val resource = userRepository.getUserList(userListPage)
        _userList.postValue(handleUserListResponse(resource))
    }

    private fun handleUserListResponse(response: SimpleResponse<UsersPageResponse>) : Resource<UsersPageResponse> {
        if(response.isSuccessful){
            //response.body()?.let {
            val it = response.body
            userListPage++
            if (userListResponse == null){
                userListResponse = it
                //userListPage = 1
            } else {
                val oldUsers = userListResponse?.data
                val newUsers = it.data
                oldUsers?.addAll(newUsers)
            }
            return Resource.Success(userListResponse ?: it)

        }
        return Resource.Error(response.exception?.message!!)
    }

    fun saveUser(user: UserModel) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun getSavedUsers() = userRepository.getSavedUsers()

    fun deleteUser(user: UserModel) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
}