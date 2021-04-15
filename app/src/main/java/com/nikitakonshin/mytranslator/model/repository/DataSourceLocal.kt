package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import io.reactivex.Single

class DataSourceLocal(
    private val remoteProvider: RoomDatabaseImplementation = RoomDatabaseImplementation()
) : IDataServer<List<DataModel>> {
    override fun getData(text: String): Single<List<DataModel>> = remoteProvider.getData(text)
}