package com.delitx.deeplapiexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.delitx.deeplapiexample.models.TargetLanguage
import com.delitx.deeplapiexample.viewmodel.TranslateEvent
import com.delitx.deeplapiexample.viewmodel.TranslateState

@Composable
fun TranslateScreen(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = state.text,
            onValueChange = { onEvent(TranslateEvent.TextChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp)
                .padding(20.dp),
            placeholder = {
                Text(text = "Введіть текст для перекладу")
            }
        )
        TargetLanguageSelector(
            selectedTargetLanguage = state.targetLanguage,
            onTargetLanguageSelected = { onEvent(TranslateEvent.TargetLanguageChanged(it)) },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.Start)
        )
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = state.translatedText ?: "",
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp)
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
            if (state.translatedText == null) {
                CircularProgressIndicator()
            }
        }
        Button(onClick = { onEvent(TranslateEvent.TranslateButtonClicked) }) {
            Text(text = "Перекласти")
        }
    }
}

@Composable
private fun TargetLanguageSelector(
    selectedTargetLanguage: TargetLanguage,
    onTargetLanguageSelected: (TargetLanguage) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        var showDropdown by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { showDropdown = true }
        ) {
            Text(
                text = selectedTargetLanguage.languageName,
                modifier = Modifier.padding(20.dp),
            )
        }
        DropdownList(
            showDropdown = showDropdown,
            onDismiss = { showDropdown = false },
            modifier = Modifier
                .padding(start = 20.dp)
                .height(400.dp)
                .background(Color.White)
                .border(width = 1.dp, color = Color.Gray)
        ) {
            LazyColumn(
                modifier = Modifier.width(200.dp)
            ) {
                items(TargetLanguage.entries) { targetLanguage ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .background(if (targetLanguage == selectedTargetLanguage) Color.Green else Color.White)
                            .clickable {
                                onTargetLanguageSelected(targetLanguage)
                                showDropdown = false
                            },
                    ) {
                        Text(
                            text = targetLanguage.languageName,
                            modifier = Modifier.padding(20.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DropdownList(
    showDropdown: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box {
        if (showDropdown) {
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                onDismissRequest = onDismiss
            ) {
                Column(
                    modifier = modifier
                        .heightIn(max = 90.dp),
                ) {
                    content()
                }
            }
        }
    }
}