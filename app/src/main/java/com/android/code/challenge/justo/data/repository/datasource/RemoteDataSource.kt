package com.android.code.challenge.justo.data.repository.datasource

import com.android.code.challenge.justo.domain.helpers.ResultWrapper
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSource {

    suspend fun <T> getUserProfileResponse(dispatcher:CoroutineDispatcher, apiCall: suspend () -> T) : ResultWrapper<T> {

        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code,errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
            }
        }

    }

    private fun convertErrorBody(throwable: HttpException): String {
        return  throwable.message() ?: "Error not found"
    }

}