package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.data.db.UserDao
import com.gmail.avoishel.usersnotebook.data.retrofit.ApiClient
import com.gmail.avoishel.usersnotebook.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

// todo ох.. 1) я просил обработать ошибки... хде? 2) а почему конструктор хилта пустой? я в нем фижу синглинстанс ретрофита нужно почитать саму суть хилта, зачем он вообще нам
// 3) почитать про солид , в данном случае над любым репозиторием должен быть интерфейс для каждой функции, которую ты хочешь здесь увидеть
// будет для тебя как клад https://github.com/JohnnySC/Lectures

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