package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel

class RepositoryImplementationLocal(private val dataSource: IDataServerLocal<List<DataModel>>): IDataServerLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(text: String): List<DataModel> {
       return dataSource.getData(text)
    }
}