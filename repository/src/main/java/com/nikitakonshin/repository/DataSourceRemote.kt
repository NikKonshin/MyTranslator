package com.nikitakonshin.repository

import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.repository.retrofit.ApiHolder
import com.nikitakonshin.repository.retrofit.IDataSource

class DataSourceRemote(private val api: IDataSource = ApiHolder().retrofitImpl()) :
    IDataServer<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> = api.search(text)
}