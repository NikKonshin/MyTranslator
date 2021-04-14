package com.nikitakonshin.mytranslator.model.entity

sealed class AppState {
    data class Success(val data: String)
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}