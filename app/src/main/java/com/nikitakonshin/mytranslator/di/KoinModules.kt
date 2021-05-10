package com.nikitakonshin.mytranslator.di

import androidx.room.Room
import com.nikitakonshin.model.entity.DataModel
import com.nikitakonshin.model.room.HistoryDao
import com.nikitakonshin.model.room.HistoryDataBase
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import com.nikitakonshin.mytranslator.view.fragments.TranslateFragment
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import com.nikitakonshin.repository.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, interactor))
}

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<IDataServer<List<DataModel>>> {
        RepositoryImplementation(DataSourceRemote())
    }

    single<IDataServerLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDatabaseImplementation(get<HistoryDao>()))
    }
}

val interactor = module {
    scope(named<TranslateFragment>()) {
       scoped {TranslateInteractor(get(), get())}
    }
    viewModel {
        TranslateViewModel(get())
    }
}

