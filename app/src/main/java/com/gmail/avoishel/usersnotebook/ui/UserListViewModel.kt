package com.gmail.avoishel.usersnotebook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import com.gmail.avoishel.usersnotebook.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserListViewModel(
    val userRepository: UserRepository
): ViewModel() {

    val userList: MutableLiveData<Resource<UsersPageResponse>> = MutableLiveData()
    var userListPage = 1
    var userListResponse: UsersPageResponse? = null

    init {
        getUserList()
    }

    fun getUserList() = viewModelScope.launch {
        userList.postValue(Resource.Loading())
        Log.i("UserListViewModel", "-----> try to get userList page = $userListPage")
        val resource = userRepository.getUserList(userListPage)
        userList.postValue(handleUserListResponse(resource))
    }

    private fun handleUserListResponse(response: Response<UsersPageResponse>) : Resource<UsersPageResponse> {
        if(response.isSuccessful){
            response.body()?.let {
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
        }
        return Resource.Error(response.message())
    }
}