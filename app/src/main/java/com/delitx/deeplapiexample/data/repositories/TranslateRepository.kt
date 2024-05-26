package com.delitx.deeplapiexample.data.repositories

import com.delitx.deeplapiexample.data.network.DeeplAPIService
import com.delitx.deeplapiexample.models.TargetLanguage
import javax.inject.Inject

class TranslateRepository @Inject constructor(
    private val deeplAPIService: DeeplAPIService,
) {
    suspend fun translateText(text: String, targetLanguage: TargetLanguage = TargetLanguage.EnglishAmerican): String {
        return deeplAPIService.translateText(text, targetLanguage.code).translations.first().text
    }
}