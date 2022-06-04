package com.gmail.avoishel.usersnotebook.retrofit

import com.gmail.avoishel.usersnotebook.data.retrofit.ApiClient
import com.gmail.avoishel.usersnotebook.data.retrofit.RetroServiceInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    //todo где то должен быть модуль хилта.. и этот класс совсем подругому будет выглядеть уже // ---> По сути RetroInstance при спользовании hilt нам не нужен не?
//
//    companion object {
//
//        private const val BASE_URL = "https://reqres.in/"
//
//        private val httpLoginInterceptor by lazy {
//            HttpLoggingInterceptor()
//        }
//
//        private val okHttpClient by lazy {
//            httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            OkHttpClient.Builder()
//                .addInterceptor(httpLoginInterceptor)
//                .build()
//        }
//
//        private val retrofit by lazy {
//            Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//        val api: RetroServiceInterface by lazy {
//            retrofit.create(RetroServiceInterface::class.java)
//        }
//
//        val apiClient = ApiClient(api)
//    }
}