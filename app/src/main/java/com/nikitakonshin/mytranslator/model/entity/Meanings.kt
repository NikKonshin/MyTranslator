package com.nikitakonshin.mytranslator.model.entity

import com.google.gson.annotations.Expose

class Meanings(
    @Expose val translation: Translation?,
    @Expose val imageUrl: String?
)
