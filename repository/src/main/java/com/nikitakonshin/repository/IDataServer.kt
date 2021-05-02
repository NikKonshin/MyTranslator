package com.nikitakonshin.repository

interface IDataServer<T> {
    suspend fun getData(text: String): T
}