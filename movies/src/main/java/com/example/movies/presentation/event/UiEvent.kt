package com.example.movies.presentation.event

internal sealed interface UiEvent {
    data class OnMovieClicked(val id: Int) : UiEvent
}