package com.gmail.avoishel.usersnotebook.retrofit

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {

        private const val BASE_URL = "https://reqres.in/"

        private val httpLoginInterceptor by lazy {
            HttpLoggingInterceptor()
        }

        private val okHttpClient by lazy {
            httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(httpLoginInterceptor)
                .build()

        }

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: RetroServiceInterface by lazy {
            retrofit.create(RetroServiceInterface::class.java)
        }
    }
}