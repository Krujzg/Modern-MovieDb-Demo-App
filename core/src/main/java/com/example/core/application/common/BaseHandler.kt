package com.example.core.application.common

import com.example.core.domain.DataError
import com.example.core.domain.constants.NetworkResponseCode
import com.example.core.domain.Result
import com.example.core.domain.RootError
import retrofit2.Response

open class BaseHandler<TResponseValue : Any> {

    protected var defaultResult = Result.Error<Unit, RootError>(DataError.Network.UNKNOWN)

    protected open fun getResultOnResponse(response: Response<TResponseValue>): Result<TResponseValue, RootError> {
        return if (checkIfResponseWasSuccessful(response)) {
            val responseBody = response.body()!!
            Result.Success(responseBody)
        } else if (response.code() == NetworkResponseCode.UN_AUTHORIZED) {
            Result.Error(DataError.Network.UNAUTHORIZED)
        } else {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    protected open fun checkIfResponseWasSuccessful(response: Response<TResponseValue>): Boolean =
        response.isSuccessful && response.body() != null
}
