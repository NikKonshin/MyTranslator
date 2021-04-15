package com.nikitakonshin.mytranslator.presenter

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.repository.DataSourceLocal
import com.nikitakonshin.mytranslator.model.repository.DataSourceRemote
import com.nikitakonshin.mytranslator.model.repository.RepositoryImplementation
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import com.nikitakonshin.mytranslator.rx.ISchedulerProvider
import com.nikitakonshin.mytranslator.rx.SchedulerProvider
import com.nikitakonshin.mytranslator.view.IView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class TranslatePresenter<T : AppState, V : IView>(
    private val interactor: TranslateInteractor = TranslateInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal()),
    ),
    private val schedulerProvider: ISchedulerProvider = SchedulerProvider(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : IPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableSingleObserver<AppState> {
        return object : DisposableSingleObserver<AppState>() {
            override fun onSuccess(t: AppState) {
                currentView?.renderData(t)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

        }
    }
}