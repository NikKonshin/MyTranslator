package com.nikitakonshin.mytranslator.model.repository

import com.nikitakonshin.mytranslator.model.entity.DataModel
import io.reactivex.Single

interface IDataServer {
   fun getData(text: String): Single<List<DataModel>>
}