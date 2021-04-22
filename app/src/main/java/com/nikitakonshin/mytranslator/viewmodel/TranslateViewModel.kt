package com.nikitakonshin.mytranslator.viewmodel

import androidx.lifecycle.LiveData
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class TranslateViewModel @Inject constructor(
    private val interactor: TranslateInteractor
) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObservable())
        )
        return super.getData(word, isOnline)
    }

    private fun getObservable(): DisposableSingleObserver<AppState> {
        return object : DisposableSingleObserver<AppState>() {
            override fun onSuccess(t: AppState) {
                appState = t
                liveDataForViewToObserve.setValue(appState)
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }
        }
    }
}