package com.gmail.avoishel.usersnotebook.retrofit

import com.gmail.avoishel.usersnotebook.data.UserModel
import com.gmail.avoishel.usersnotebook.data.UsersPageResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("users")
    fun getUsersList(): Call<UsersPageResponse>
}