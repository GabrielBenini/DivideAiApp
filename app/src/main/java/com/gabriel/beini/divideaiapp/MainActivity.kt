package com.gabriel.beini.divideaiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gabriel.beini.divideaiapp.core.ui.theme.DivideAiAppTheme
import com.gabriel.beini.divideaiapp.feature.user.presentation.screen.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DivideAiAppTheme {
                UserScreen()
            }
        }
    }
}