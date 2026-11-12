package com.znak.launcher
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.znak.launcher.ui.LauncherScreen
import com.znak.launcher.ui.ZnakLauncherTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZnakLauncherTheme {
                LauncherScreen()
            }
        }
    }
}
