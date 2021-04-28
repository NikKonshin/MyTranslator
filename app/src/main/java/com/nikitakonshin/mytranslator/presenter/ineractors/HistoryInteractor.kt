package com.nikitakonshin.mytranslator.presenter.ineractors

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import com.nikitakonshin.mytranslator.model.repository.IDataServerLocal

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