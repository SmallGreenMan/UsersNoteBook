package com.gmail.avoishel.usersnotebook.repository

import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import javax.inject.Inject

// todo ох.. 1) я просил обработать ошибки... хде? 2) а почему конструктор хилта пустой? я в нем фижу синглинстанс ретрофита нужно почитать саму суть хилта, зачем он вообще нам
// 3) почитать про солид , в данном случае над любым репозиторием должен быть интерфейс для каждой функции, которую ты хочешь здесь увидеть
// будет для тебя как клад https://github.com/JohnnySC/Lectures
class UserRepository @Inject constructor() {

    suspend fun getUserList(pageNumber: Int) =
        //RetroInstance.api.getUsersList(pageNumber)
        RetroInstance.apiClient.getUsersListByPage(pageNumber)

}