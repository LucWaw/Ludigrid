package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.lucwaw.ludigrid.screen.detail.DetailScreen
import com.lucwaw.ludigrid.screen.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(windowSizeClass: WindowSizeClass) {
    var currentScreen by rememberSaveable { mutableStateOf(Screen.HOME) }
    MaterialTheme {

        if (currentScreen == Screen.HOME || currentScreen == Screen.PROFILE) {
            NavigationSuiteScaffold(
                navigationSuiteItems = {
                    item(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentScreen == Screen.HOME,
                        onClick = { currentScreen = Screen.HOME }
                    )
                    item(
                        icon = { Icon(Icons.Default.AccountBox, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = currentScreen == Screen.PROFILE,
                        onClick = { currentScreen = Screen.PROFILE }
                    )
                }
            ) {
                when (currentScreen) {
                    Screen.HOME -> HomeScreen(
                        windowSizeClass = windowSizeClass,
                        onNavigateToDetail = { currentScreen = Screen.DETAIL }
                    )
                    Screen.PROFILE -> ProfileScreen()
                    else -> {}
                }
            }
        } else {
            // Pas de bottom bar ici
            when (currentScreen) {
                Screen.DETAIL -> DetailScreen()
                else -> {}
            }
        }
    }

}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize()){
        Text("Profile")
    }
}




enum class Screen(
) {
    HOME(),
    PROFILE(),
    DETAIL()
}