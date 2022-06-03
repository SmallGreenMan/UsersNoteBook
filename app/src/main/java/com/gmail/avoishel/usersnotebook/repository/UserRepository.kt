package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.data.db.UserDao
import com.gmail.avoishel.usersnotebook.data.retrofit.ApiClient
import com.gmail.avoishel.usersnotebook.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val db: UserDao,
    private val userApi: ApiClient
) {

    suspend fun getUserList(pageNumber: Int) =
        //RetroInstance.apiClient.getUsersListByPage(pageNumber)
        userApi.getUsersListByPage(pageNumber)

    suspend fun insertUser(user: UserModel) = db.insert(user)

    fun getSavedUsers() = db.getAllUsers()

    suspend fun deleteUser(user: UserModel) = db.deleteUser(user)

}