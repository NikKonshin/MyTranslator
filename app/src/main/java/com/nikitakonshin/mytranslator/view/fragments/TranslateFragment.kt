package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikitakonshin.mytranslator.R
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.presenter.IPresenter
import com.nikitakonshin.mytranslator.view.IView

class TranslateFragment : BaseFragment<AppState>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

    override fun createPresenter(): IPresenter<AppState, IView> {
        TODO("Not yet implemented")
    }

}