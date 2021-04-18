package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.retrofit.ApiHolder
import com.nikitakonshin.mytranslator.model.repository.retrofit.IDataSource
import io.reactivex.Single

class DataSourceRemote(private val api: IDataSource = ApiHolder().retrofitImpl()) :
    IDataServer<List<DataModel>> {
    override fun getData(text: String): Single<List<DataModel>> = api.search(text)
}