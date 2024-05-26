package com.delitx.deeplapiexample.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import com.delitx.deeplapiexample.models.TargetLanguage

sealed class TranslateEvent {
    data class TextChanged(val text: TextFieldValue) : TranslateEvent()
    data object TranslateButtonClicked : TranslateEvent()
    data class TargetLanguageChanged(val targetLanguage: TargetLanguage) : TranslateEvent()
}