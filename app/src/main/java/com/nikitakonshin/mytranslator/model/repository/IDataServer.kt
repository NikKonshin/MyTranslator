package com.nikitakonshin.mytranslator.model.repository

interface IDataServer<T> {
    suspend fun getData(text: String): T
}