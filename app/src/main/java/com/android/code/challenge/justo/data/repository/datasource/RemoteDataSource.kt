package com.android.code.challenge.justo.data.repository.datasource

import androidx.lifecycle.MutableLiveData
import com.android.code.challenge.justo.data.retrofit.JustoClient
import com.android.code.challenge.justo.data.retrofit.JustoService
import com.android.code.challenge.justo.data.retrofit.response.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val mJustoClient: JustoClient = JustoClient.instance!!
    private val mJustoService: JustoService = mJustoClient.getService()

    fun getUserProfile() : MutableLiveData<UserProfile> {


        val userProfileResponse = MutableLiveData<UserProfile>()

        val call : Call<UserProfile> = mJustoService.getUserProfile()

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.code() == 200){
                        userProfileResponse.value = UserProfile(true, response.body()!!.info, response.body()!!.results, "")
                    } else {
                        userProfileResponse.value = UserProfile(false, response.body()!!.info, response.body()!!.results, response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    userProfileResponse.value = UserProfile(false, null, null, t.message)
                }

            })
        }

        return userProfileResponse
    }
}