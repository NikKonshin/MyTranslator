package com.nikitakonshin.mytranslator.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.presenter.IPresenter
import com.nikitakonshin.mytranslator.view.IView

abstract class BaseFragment<T: AppState> : Fragment(), IView{
    protected lateinit var presenter: IPresenter<T, IView>

    abstract override fun renderData(appState: AppState)
    protected abstract fun createPresenter() : IPresenter<T, IView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}