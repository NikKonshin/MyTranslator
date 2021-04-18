package com.nikitakonshin.mytranslator.presenter.ineractors

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import io.reactivex.Single

class TranslateInteractor(
    private val remoteRepository: IDataServer<List<DataModel>>,
    private val localRepository: IDataServer<List<DataModel>>
) : Intetactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Single<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}