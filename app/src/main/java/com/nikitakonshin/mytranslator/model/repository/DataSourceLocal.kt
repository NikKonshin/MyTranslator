package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDatabaseImplementation = RoomDatabaseImplementation()
) : IDataServer<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> = remoteProvider.getData(text)
}