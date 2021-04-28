package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.AppState
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.room.HistoryDao
import com.nikitakonshin.mytranslator.utils.convertDataModelSuccessToEntity
import com.nikitakonshin.mytranslator.utils.mapHistoryEntityToSearchResult

class RoomDatabaseImplementation(private val historyDao: HistoryDao) :
    IDataServerLocal<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> =
        mapHistoryEntityToSearchResult(historyDao.getAll())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}