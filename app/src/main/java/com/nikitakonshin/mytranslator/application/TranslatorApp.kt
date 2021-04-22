package com.nikitakonshin.mytranslator.application

import android.app.Application
import com.nikitakonshin.mytranslator.di.AppComponent
import com.nikitakonshin.mytranslator.di.DaggerAppComponent

class TranslatorApp : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val component = DaggerAppComponent.builder().appComponent(this).build()
        TranslatorApp.component = component
    }
}