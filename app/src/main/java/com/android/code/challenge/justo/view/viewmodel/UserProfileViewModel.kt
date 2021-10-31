package com.android.code.challenge.justo.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.*
import com.android.code.challenge.justo.domain.usecases.GetUserProfileUseCase
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {


    private val mGetUserProfileUseCase = GetUserProfileUseCase()
    val userProfileResult = MutableLiveData<Result>()
    val userProfileError = MutableLiveData<String>()


    fun getUserProfileResponse(){
        viewModelScope.launch {

            val response = mGetUserProfileUseCase.getUserProfile()
            when (response) {
                is ResultWrapper.Success -> userProfileResult.postValue(setResultValue(response.value.results))
                is ResultWrapper.NetworkError -> userProfileError.postValue(response.toString())
                is ResultWrapper.GenericError -> userProfileError.postValue(response.code.toString() + " " + response.error)

            }
        }

    }

    private fun setResultValue(results: List<Result>?): Result {
        return  results?.get(0)?.let { Result(it.cell,it.dob,it.email,it.gender,it.id,it.location,it.login,it.name,it.nat,it.phone,it.picture,it.registered) }
            ?: getEmptyResultObject()
    }

    private fun getEmptyResultObject(): Result {
       return Result(
            "",
            Dob(0, ""), "", "",
            Id("", ""),
            Location(
                "",
                Coordinates("", ""), "", "", "",
                Street("", 0),
                Timezone("", "")
            ),
            Login("", "", "", "", "", "", ""),
            Name("", "", ""),
            "", "",
            Picture("", "", ""),
            Registered(0, "")
        )
    }

}