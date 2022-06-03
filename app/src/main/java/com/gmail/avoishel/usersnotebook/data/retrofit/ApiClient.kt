package com.gmail.avoishel.usersnotebook.data.retrofit

import android.util.Log
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ApiClient  @Inject constructor(
    private val retroServiceInterface: RetroServiceInterface
) {
    suspend fun getUsersListByPage(pageNumber: Int): SimpleResponse<UsersPageResponse> {
        return safeApiCall { retroServiceInterface.getUsersList(pageNumber) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            when(e){
                is IOException ->  Log.e("ApiClient", "----> Network Failure, e:$e")
                else -> Log.e("ApiClient", "----> Conversion Error, e:$e")
            }
            SimpleResponse.failure(e)
        }
    }
}