package com.example.core.domain

sealed interface DataError: Error {
    enum class Network: DataError {
        UNAUTHORIZED,
        NO_INTERNET,
        SERVER_ERROR,
        UNKNOWN
    }
}