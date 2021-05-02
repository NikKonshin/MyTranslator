package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel

class RepositoryImplementation(private val dataSource: IDataServer<List<DataModel>>) :
    IDataServer<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> {
        return dataSource.getData(text)
    }
}