package com.nikitakonshin.mytranslator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikitakonshin.mytranslator.view.fragments.TranslateFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, TranslateFragment())
            .commit()
    }
}