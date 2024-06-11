package com.example.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.core.presentation.states.AbstractUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

abstract class AbstractViewModel<T : AbstractUIState>(initialUIState: T) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialUIState)
    val uiState: StateFlow<T> = _uiState.asStateFlow()

    private suspend fun hideLoginDialog() {
        _uiState.value.hideLoadingDialog()
    }

    protected suspend fun onError(message: String) {
        withContext(Dispatchers.Main.immediate) {
            hideLoginDialog()
            showSnackbar(message)
        }
    }

    private suspend fun showSnackbar(message: String) {
        _uiState.value.showSnackBar(message)
    }

    protected suspend fun onLoading() {
        withContext(Dispatchers.Main.immediate) {
            _uiState.value.showLoadingDialog()
        }
    }
}