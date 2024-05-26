package com.delitx.deeplapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.delitx.deeplapiexample.components.TranslateScreen
import com.delitx.deeplapiexample.ui.theme.DeeplAPIExampleTheme
import com.delitx.deeplapiexample.viewmodel.TranslateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeeplAPIExampleTheme {
                val viewModel = hiltViewModel<TranslateViewModel>()
                val state by viewModel.stateFlow.collectAsState()
                TranslateScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) },
                    modifier = Modifier.fillMaxSize()
                        .systemBarsPadding()
                )
            }
        }
    }
}
