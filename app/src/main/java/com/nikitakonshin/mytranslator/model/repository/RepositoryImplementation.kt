package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import io.reactivex.Single

class RepositoryImplementation(private val dataSource: IDataServer<List<DataModel>>) :
    IDataServer<List<DataModel>> {
    override fun getData(text: String): Single<List<DataModel>> {
        return dataSource.getData(text)
    }
}