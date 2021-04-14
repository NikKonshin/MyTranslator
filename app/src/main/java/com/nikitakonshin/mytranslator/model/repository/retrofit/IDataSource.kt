package com.nikitakonshin.mytranslator.model.repository.retrofit

import com.nikitakonshin.mytranslator.model.entity.DataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {

    @GET("words/search")
    fun search(@Query("search") word: String): Single<List<DataModel>>
}