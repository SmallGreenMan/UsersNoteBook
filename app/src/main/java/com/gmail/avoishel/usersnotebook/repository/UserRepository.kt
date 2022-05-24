package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.db.UserDatabase
import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import javax.inject.Inject

class UserRepository @Inject constructor(
    val db: UserDatabase
) {

    suspend fun getUserList(pageNumber: Int) =
        //RetroInstance.api.getUsersList(pageNumber)
        RetroInstance.apiClient.getUsersListByPage(pageNumber)

}