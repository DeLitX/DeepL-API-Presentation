package com.delitx.deeplapiexample.data.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DeeplAPIService {
    @FormUrlEncoded
    @POST("translate")
    suspend fun translateText(
        @Field("text") text: String,
        @Field("target_lang") targetLanguage: String
    ): TranslateResponse
}