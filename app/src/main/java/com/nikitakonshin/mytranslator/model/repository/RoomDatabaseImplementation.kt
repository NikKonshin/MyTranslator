package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel

class RoomDatabaseImplementation : IDataServer<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}