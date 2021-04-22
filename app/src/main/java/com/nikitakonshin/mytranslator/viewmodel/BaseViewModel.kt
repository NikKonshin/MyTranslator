package com.nikitakonshin.mytranslator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.rx.ISchedulerProvider
import com.nikitakonshin.mytranslator.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val schedulerProvider: ISchedulerProvider = SchedulerProvider()
) : ViewModel() {
    open fun getLiveData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    protected val coroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(t: Throwable)
}