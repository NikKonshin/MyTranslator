package com.nikitakonshin.mytranslator.di

import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.DataSourceLocal
import com.nikitakonshin.mytranslator.model.repository.DataSourceRemote
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import com.nikitakonshin.mytranslator.model.repository.RepositoryImplementation
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single<IDataServer<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(DataSourceRemote())
    }

    single<IDataServer<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(DataSourceLocal())
    }
}

val interactor = module {
    single {
        TranslateInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL)))
    }
    viewModel {
        TranslateViewModel(get())
    }
}