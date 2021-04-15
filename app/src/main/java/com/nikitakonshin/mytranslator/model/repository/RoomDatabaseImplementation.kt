package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import io.reactivex.Single

class RoomDatabaseImplementation: IDataServer<List<DataModel>> {
    override fun getData(text: String): Single<List<DataModel>> {
        TODO("Not yet implemented")
    }
}