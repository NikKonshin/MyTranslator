package com.nikitakonshin.model.entity

import com.google.gson.annotations.Expose

class DataModel(
    @Expose val text: String?,
    @Expose val meanings: List<Meanings>?
)
