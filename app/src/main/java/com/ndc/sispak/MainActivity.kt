package com.ndc.sispak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.ndc.sispak.ui.navigation.SetupNavHost
import com.ndc.sispak.ui.theme.SisPakTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            var lightStatusBar by rememberSaveable {
                mutableStateOf(true)
            }
            SisPakTheme(
                lightStatusBars = lightStatusBar
            ) {
                SetupNavHost(
                    navHostController = navHostController,
                    onStatusBarChange = {
                        lightStatusBar = it
                    }
                )
            }
        }
    }
}