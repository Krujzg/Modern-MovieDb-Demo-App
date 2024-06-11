package com.example.core.presentation.states

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AbstractUIState {
    private val _snackBarMessageState: MutableSharedFlow<String> = MutableSharedFlow()
    val snackBarMessageState: SharedFlow<String> = _snackBarMessageState.asSharedFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    suspend fun showSnackBar(message: String) {
        _snackBarMessageState.emit(message)
    }

    suspend fun showLoadingDialog() {
        _isLoading.emit(true)
    }

    suspend fun hideLoadingDialog() {
        _isLoading.emit(false)
    }
}