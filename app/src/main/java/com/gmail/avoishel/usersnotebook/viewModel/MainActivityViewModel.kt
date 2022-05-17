package com.gmail.avoishel.usersnotebook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.avoishel.usersnotebook.models.UsersPageResponse

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