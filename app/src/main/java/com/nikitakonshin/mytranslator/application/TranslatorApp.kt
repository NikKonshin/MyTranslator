package com.nikitakonshin.mytranslator.application

import android.app.Application
import com.nikitakonshin.mytranslator.di.*
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(application, interactor)) }
    }
}