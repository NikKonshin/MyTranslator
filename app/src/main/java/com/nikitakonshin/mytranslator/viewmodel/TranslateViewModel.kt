package com.nikitakonshin.mytranslator.viewmodel

import androidx.lifecycle.LiveData
import com.nikitakonshin.core.viewmodel.BaseViewModel
import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TranslateViewModel(
    private val interactor: TranslateInteractor
) :
    BaseViewModel<AppState>() {

    override fun getLiveData(word: String, isOnline: Boolean): LiveData<AppState> {
        liveDataForViewToObserve.value = AppState.Loading(null)

        coroutineScope.launch { startInteractor(word, isOnline) }
        return liveDataForViewToObserve
    }

    override fun handleError(t: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(t))
    }

    private suspend fun startInteractor(word: String, online: Boolean) =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(interactor.getData(word, online))
        }
}

