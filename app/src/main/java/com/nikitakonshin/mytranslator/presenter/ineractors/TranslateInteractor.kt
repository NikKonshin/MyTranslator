package com.nikitakonshin.mytranslator.presenter.ineractors

import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.repository.IDataServer
import com.nikitakonshin.repository.IDataServerLocal

class TranslateInteractor(
    private val remoteRepository: IDataServer<List<DataModel>>,
    private val localRepository: IDataServerLocal<List<DataModel>>
) : Intetactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}