package com.nikitakonshin.mytranslator.presenter.ineractors

import io.reactivex.Single

interface Intetactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Single<T>
}