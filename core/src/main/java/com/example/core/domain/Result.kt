package com.example.core.domain

typealias RootError = Error

sealed interface Result<out D, out E: Any> {
    data class Success<out D, out E: RootError>(val data: D): Result<D, E>
    data class Error<out D, out E: RootError>(val error: E): Result<D, E>
    data class Loading<out D, out E: RootError>(val loadingMsg: String) : Result<D, E>
}