package com.nikitakonshin.mytranslator.viewmodel

import androidx.lifecycle.LiveData
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.presenter.ineractors.HistoryInteractor
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) : BaseViewModel<AppState>() {

    override fun getLiveData(word: String, isOnline: Boolean): LiveData<AppState> {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        coroutineScope.launch { startInteractor(word, isOnline) }
        return liveDataForViewToObserve
    }

    private suspend fun startInteractor(word: String, online: Boolean) {
        liveDataForViewToObserve.postValue(interactor.getData(word, online))
    }

    override fun handleError(t: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(t))
    }
}