package com.nikitakonshin.repository

import com.nikitakonshin.model.entity.AppState

interface IDataServerLocal<T> : IDataServer<T> {
    suspend fun saveToDB(appState: AppState)
}