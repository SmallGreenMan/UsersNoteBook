package com.gmail.avoishel.usersnotebook.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.avoishel.usersnotebook.data.UserModel
import com.gmail.avoishel.usersnotebook.data.UsersPageResponse
import com.gmail.avoishel.usersnotebook.retrofit.RetroInstance
import com.gmail.avoishel.usersnotebook.retrofit.RetroServiceInterface
import com.gmail.avoishel.usersnotebook.ui.UserListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var liveDataUserList: MutableLiveData<UsersPageResponse> = MutableLiveData()


    fun getLiveDataObserver(): MutableLiveData<UsersPageResponse> {
        return liveDataUserList
    }

//    fun makeApiCall(){
//        //val retrofitInstance = RetroInstance.getRetrofitInstance()
//        //val retrofitService = retrofitInstance.create(RetroServiceInterface::class.java)
//        val call = retrofitService.getUsersList()
//
//        call.enqueue(object : Callback<UsersPageResponse>{
//
//            override fun onResponse(
//                call: Call<UsersPageResponse>,
//                response: Response<UsersPageResponse>
//            ) {
//                Log.i("FB", "----------> "+response.body().toString())
//                liveDataUserList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<UsersPageResponse>, t: Throwable) {
//                Log.i("FB", "----------> Network request error")
//                liveDataUserList.postValue(null)
//            }
//        })
//    }
}