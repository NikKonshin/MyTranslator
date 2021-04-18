package com.nikitakonshin.mytranslator.model.repository

import io.reactivex.Single

interface IDataServer<T> {
    fun getData(text: String): Single<T>
}