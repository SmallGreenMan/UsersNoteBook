package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.data.db.UserDao
import com.gmail.avoishel.usersnotebook.data.models.UserModel
import com.gmail.avoishel.usersnotebook.data.retrofit.RetroServiceInterface
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val db: UserDao,
    private val userApi: RetroServiceInterface
) {

    suspend fun getUserList(pageNumber: Int) =
        //RetroInstance.apiClient.getUsersListByPage(pageNumber)
        userApi.getUsersList(pageNumber) // getUsersListByPage(pageNumber)

    suspend fun insertUser(user: UserModel) = db.insert(user)

    fun getSavedUsers() = db.getAllUsers()

    suspend fun deleteUser(user: UserModel) = db.deleteUser(user)

}