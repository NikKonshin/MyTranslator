package com.nikitakonshin.mytranslator.presenter.ineractors

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import io.reactivex.Single
import kotlinx.coroutines.Deferred

class TranslateInteractor(
    private val remoteRepository: IDataServer<List<DataModel>>,
    private val localRepository: IDataServer<List<DataModel>>
) : Intetactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(if (fromRemoteSource) {
            remoteRepository.getData(word)
        } else {
            localRepository.getData(word)
        })
    }
}