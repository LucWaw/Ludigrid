package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


@Composable
expect fun BoxScope.ToolBar(modifier: Modifier = Modifier, share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit)


@Composable
expect fun FAB(onClick: () -> Unit)

@Composable
expect fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit)