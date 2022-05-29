package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.db.UserDao
import com.gmail.avoishel.usersnotebook.db.UserDatabase
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val db: UserDao
) {

    suspend fun getUserList(pageNumber: Int) =
        //RetroInstance.api.getUsersList(pageNumber)
        RetroInstance.apiClient.getUsersListByPage(pageNumber)

    suspend fun upsert(user: UserModel) = db.upSetr(user)

    fun getSavedUsers() = db.getAllUsers()

    suspend fun deleteUser(user: UserModel) = db.deleteUser(user)

}