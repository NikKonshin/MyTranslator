package com.nikitakonshin.mytranslator.di

import android.content.Context
import com.nikitakonshin.mytranslator.di.modules.InteractorModule
import com.nikitakonshin.mytranslator.di.modules.RepositoryModule
import com.nikitakonshin.mytranslator.di.modules.ViewModelModule
import com.nikitakonshin.mytranslator.view.fragments.TranslateFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: TranslateFragment)
}