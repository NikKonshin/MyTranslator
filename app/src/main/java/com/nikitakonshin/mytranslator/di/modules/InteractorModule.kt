package com.nikitakonshin.mytranslator.di.modules

import com.nikitakonshin.mytranslator.di.NAME_LOCAL
import com.nikitakonshin.mytranslator.di.NAME_REMOTE
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import com.nikitakonshin.mytranslator.presenter.ineractors.TranslateInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: IDataServer<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: IDataServer<List<DataModel>>
    ) = TranslateInteractor(repositoryRemote, repositoryLocal)
}