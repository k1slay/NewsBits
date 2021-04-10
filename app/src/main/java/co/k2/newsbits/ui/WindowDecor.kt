package co.k2.newsbits.ui

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb

@Composable
fun ColorStatusBar(window: Window) {
    window.statusBarColor = MaterialTheme.colors.background.toArgb()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val flag = if (isSystemInDarkTheme())
            0
        else
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        window.decorView.windowInsetsController?.setSystemBarsAppearance(
            flag, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (isSystemInDarkTheme().not()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
