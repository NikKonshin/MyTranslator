package com.nikitakonshin.mytranslator.di.modules

import com.nikitakonshin.mytranslator.di.NAME_LOCAL
import com.nikitakonshin.mytranslator.di.NAME_LOCAL_DATA_SOURCE
import com.nikitakonshin.mytranslator.di.NAME_REMOTE
import com.nikitakonshin.mytranslator.di.NAME_REMOTE_DATA_SOURCE
import com.nikitakonshin.mytranslator.model.entity.DataModel
import com.nikitakonshin.mytranslator.model.repository.DataSourceLocal
import com.nikitakonshin.mytranslator.model.repository.DataSourceRemote
import com.nikitakonshin.mytranslator.model.repository.IDataServer
import com.nikitakonshin.mytranslator.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun providesRepositoryRemote(
        @Named(NAME_REMOTE_DATA_SOURCE) dataSourceRemote: IDataServer<List<DataModel>>
    ): IDataServer<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun providesRepositoryLocal(
        @Named(NAME_LOCAL_DATA_SOURCE) dataSourceLocal: IDataServer<List<DataModel>>
    ): IDataServer<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE_DATA_SOURCE)
    internal fun providesDataSourceRemote(): IDataServer<List<DataModel>> = DataSourceRemote()

    @Provides
    @Singleton
    @Named(NAME_LOCAL_DATA_SOURCE)
    internal fun providesDataSourceLocal(): IDataServer<List<DataModel>> = DataSourceLocal()
}