package com.gmail.avoishel.usersnotebook.data.retrofit

import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("api/users")
    suspend fun getUsersList(
        @Query("page")
        pageNumber: Int = 1
    ): Response<UsersPageResponse>
}