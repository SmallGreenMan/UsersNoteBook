package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import javax.inject.Inject

class UserRepository @Inject constructor() {

    suspend fun getUserList(pageNumber: Int) =
        RetroInstance.api.getUsersList(pageNumber)
}