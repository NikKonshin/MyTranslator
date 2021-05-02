package com.nikitakonshin.repository

import com.nikitakonshin.model.entity.AppState
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.model.room.HistoryDao
import com.nikitakonshin.utils.convertDataModelSuccessToEntity
import com.nikitakonshin.utils.mapHistoryEntityToSearchResult

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