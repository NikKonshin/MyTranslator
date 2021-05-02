package com.nikitakonshin.mytranslator.di

import androidx.room.Room
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.*
import com.nikitakonshin.mytranslator.model.room.HistoryDao
import com.nikitakonshin.mytranslator.model.room.HistoryDataBase
import com.nikitakonshin.mytranslator.presenter.ineractors.HistoryInteractor
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import com.nikitakonshin.mytranslator.viewmodel.HistoryViewModel
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
    single {
        TranslateInteractor(get(), get())
    }
    viewModel {
        TranslateViewModel(get())
    }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}