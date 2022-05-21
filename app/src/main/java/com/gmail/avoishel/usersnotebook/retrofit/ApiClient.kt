package com.gmail.avoishel.usersnotebook.retrofit

import android.util.Log
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import retrofit2.Response

class ApiClient(
    private val retroServiceInterface: RetroServiceInterface
) {
    suspend fun getUsersListByPage(pageNumber: Int): SimpleResponse<UsersPageResponse> {
        return safeApiCall { retroServiceInterface.getUsersList(pageNumber) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            Log.e("ApiClient", "----> safeApiCall Error, e:$e")
            SimpleResponse.failure(e)
        }
    }
}