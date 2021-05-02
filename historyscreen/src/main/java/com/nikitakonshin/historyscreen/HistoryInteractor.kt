package com.nikitakonshin.historyscreen

import com.nikitakonshin.core.viewmodel.Intetactor
import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.repository.IDataServer
import com.nikitakonshin.repository.IDataServerLocal

class HistoryInteractor(
    private val repositoryRemote: IDataServer<List<DataModel>>,
    private val repositoryLocal: IDataServerLocal<List<DataModel>>
) : Intetactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}