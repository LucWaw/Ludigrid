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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.lucwaw.ludigrid.screen.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.home_route
import ludigrid.composeapp.generated.resources.profile_route
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun App(windowSizeClass: WindowSizeClass) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    MaterialTheme {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                it.icon,
                                contentDescription = stringResource(it.label)
                            )
                        },
                        label = { Text(stringResource(it.label)) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }
                    )
                }
            }
        ) {
            when (currentDestination) {
                AppDestinations.HOME -> HomeScreen(windowSizeClass)
                AppDestinations.PROFILE -> ProfileScreen()
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




enum class AppDestinations(
    val label: StringResource,
    val icon: ImageVector
) {
    HOME(Res.string.home_route, Icons.Default.Home),
    PROFILE(Res.string.profile_route, Icons.Default.AccountBox),
}
