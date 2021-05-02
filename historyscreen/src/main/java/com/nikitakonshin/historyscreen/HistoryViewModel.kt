package com.nikitakonshin.historyscreen

import androidx.lifecycle.LiveData
import com.nikitakonshin.core.viewmodel.BaseViewModel
import com.nikitakonshin.model.entity.AppState
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