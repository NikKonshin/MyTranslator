package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.AppState

interface IDataServerLocal<T>: IDataServer<T> {
 suspend fun saveToDB(appState: AppState)
}