package com.nikitakonshin.mytranslator.presenter.ineractors

interface Intetactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}