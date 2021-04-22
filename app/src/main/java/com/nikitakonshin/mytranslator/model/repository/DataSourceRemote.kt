package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.retrofit.ApiHolder
import com.nikitakonshin.mytranslator.model.repository.retrofit.IDataSource

class DataSourceRemote(private val api: IDataSource = ApiHolder().retrofitImpl()) :
    IDataServer<List<DataModel>> {
    override suspend fun getData(text: String): List<DataModel> = api.search(text)
}