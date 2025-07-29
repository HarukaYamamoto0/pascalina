package com.harukadev.pascalina

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.harukadev.pascalina.calculator.presentation.MainScreen
import com.harukadev.pascalina.ui.theme.PascalinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PascalinaTheme {
                val context = LocalContext.current
                val window = (context as Activity).window
                val view = LocalView.current

                val statusBarColor = Color(0xff2b352e)
                val navigationBarColor = Color(0xff1a1c1a)

                LaunchedEffect(true) {
                    val insetsController = WindowCompat.getInsetsController(window, view)

                    // Configure icon appearance
                    insetsController.isAppearanceLightStatusBars = false
                    insetsController.isAppearanceLightNavigationBars = false

                    // For API 35+, we no longer define colors directly
                    // The system automatically manages colors edge-to-edge

                    // If you still need to set colors (older APIs):
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                        @Suppress("DEPRECATION")
                        window.statusBarColor = statusBarColor.toArgb()
                        @Suppress("DEPRECATION")
                        window.navigationBarColor = navigationBarColor.toArgb()
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}