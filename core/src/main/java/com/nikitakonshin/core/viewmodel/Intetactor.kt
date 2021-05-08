package com.nikitakonshin.core.viewmodel

interface Intetactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}