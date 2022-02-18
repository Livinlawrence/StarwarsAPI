package com.livin.starwars.common

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}