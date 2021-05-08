package com.nikitakonshin.repository.retrofit

import com.nikitakonshin.model.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {

    @GET("words/search")
    suspend fun search(@Query("search") word: String): List<DataModel>
}