package com.nikitakonshin.mytranslator.view

import com.nikitakonshin.model.entity.AppState

interface IView {
    fun renderData(appState: AppState)
}