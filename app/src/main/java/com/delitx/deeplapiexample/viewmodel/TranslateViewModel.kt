package com.delitx.deeplapiexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delitx.deeplapiexample.data.repositories.TranslateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    private val translateRepository: TranslateRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(TranslateState())
    val stateFlow: StateFlow<TranslateState> = _stateFlow.asStateFlow()

    fun handleEvent(event: TranslateEvent) {
        when (event) {
            is TranslateEvent.TextChanged -> _stateFlow.update { it.copy(text = event.text) }
            TranslateEvent.TranslateButtonClicked -> translateText()
            is TranslateEvent.TargetLanguageChanged ->
                _stateFlow.update { it.copy(targetLanguage = event.targetLanguage) }
        }
    }

    private fun translateText() {
        val state = stateFlow.value
        viewModelScope.launch {
            _stateFlow.update { it.copy(translatedText = null) }
            val translatedText = translateRepository.translateText(
                state.text.text,
                state.targetLanguage
            )
            _stateFlow.update { it.copy(translatedText = translatedText) }
        }
    }
}