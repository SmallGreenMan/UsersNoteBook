package com.gmail.avoishel.usersnotebook.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.utility.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserListViewModel(
    val userRepository: UserRepository
): ViewModel() {

    val userList: MutableLiveData<Resource<UsersPageResponse>> = MutableLiveData()
    var userListPage = 1

    init {
        getUserList()
    }

    fun getUserList() = viewModelScope.launch {
        userList.postValue(Resource.Loading())
        val resource = userRepository.getUserList(userListPage)
        userList.postValue(handleUserListResponse(resource))
    }

    private fun handleUserListResponse(response: Response<UsersPageResponse>) : Resource<UsersPageResponse> {
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}