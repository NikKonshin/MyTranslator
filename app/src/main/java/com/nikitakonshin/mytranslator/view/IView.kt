package com.nikitakonshin.mytranslator.view

import com.nikitakonshin.mytranslator.model.entity.AppState

interface IView {
    fun renderData(appState: AppState)
}