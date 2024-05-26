package com.delitx.deeplapiexample.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import com.delitx.deeplapiexample.models.TargetLanguage

data class TranslateState(
    val text: TextFieldValue = TextFieldValue(""),
    val translatedText: String? = "",
    val targetLanguage: TargetLanguage = TargetLanguage.EnglishAmerican,
)
