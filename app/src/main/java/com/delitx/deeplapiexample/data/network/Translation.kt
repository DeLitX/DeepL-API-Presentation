package com.delitx.deeplapiexample.data.network

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val text: String,
    @SerializedName("detected_source_language")
    val detectedSourceLanguage: String,
)
