package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import com.gmail.avoishel.usersnotebook.retrofit.SimpleResponse
import javax.inject.Inject

class UserRepository @Inject constructor() {

    suspend fun getUserList(pageNumber: Int): SimpleResponse<UsersPageResponse> {
        //RetroInstance.api.getUsersList(pageNumber)
        val request = RetroInstance.apiClient.getUsersListByPage(pageNumber)

//        if (request.failed || !request.isSuccessful){
//            return null
//        }

        return request
    }
}