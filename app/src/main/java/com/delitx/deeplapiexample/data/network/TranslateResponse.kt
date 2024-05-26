package com.delitx.deeplapiexample.data.network

import com.google.gson.annotations.SerializedName

data class TranslateResponse(
    @SerializedName("translations")
    val translations: List<Translation>,
)