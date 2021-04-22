package com.nikitakonshin.mytranslator.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitakonshin.mytranslator.viewmodel.TranslateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TranslateViewModel::class)
    protected abstract fun translateViewModel(mainViewModel: TranslateViewModel): ViewModel
}