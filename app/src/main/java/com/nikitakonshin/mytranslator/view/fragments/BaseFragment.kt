package com.nikitakonshin.mytranslator.view.fragments

import androidx.fragment.app.Fragment
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: AppState)
}