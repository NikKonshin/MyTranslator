package com.nikitakonshin.mytranslator.presenter

import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.mytranslator.view.IView

interface IPresenter<T : AppState, V: IView> {
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}