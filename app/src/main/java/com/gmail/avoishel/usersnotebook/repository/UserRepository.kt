package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance

class UserRepository() {

    suspend fun getUserList(pageNumber: Int) =
        RetroInstance.api.getUsersList(pageNumber)
}