package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


@Composable
expect fun BoxScope.ToolBar(modifier: Modifier = Modifier)