package com.android.code.challenge.justo.view.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import com.android.code.challenge.justo.domain.model.*
import com.android.code.challenge.justo.domain.usecases.GetUserProfileUseCase
import io.reactivex.Observable
import kotlinx.coroutines.launch


class UserProfileViewModel @ViewModelInject constructor(private val getUserProfileUseCase: GetUserProfileUseCase) : ViewModel() {


    var userProfileResult:Observable<Result> = Observable.empty()

    fun getUserProfileResponseRx(){
        viewModelScope.launch {
            when (val response = getUserProfileUseCase.getUserProfile()) {
                is ResultWrapper.Success -> { userProfileResult = Observable.just(setResultValue(response.value.results))
                Log.e("ActualResponse", setResultValue(response.value.results).name.first)
                }
                is ResultWrapper.NetworkError -> userProfileResult.doOnError {  (Throwable(response.toString()))}
                is ResultWrapper.GenericError -> userProfileResult.doOnError{Throwable(response.code.toString() + " " + response.error)}
            }
        }
    }

    private fun setResultValue(results: List<Result>?): Result {
        return  results?.get(0)?.let { Result(it.cell,it.dob,it.email,it.gender,it.id,it.location,it.login,it.name,it.nat,it.phone,it.picture,it.registered) }?: getEmptyResultObject()
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