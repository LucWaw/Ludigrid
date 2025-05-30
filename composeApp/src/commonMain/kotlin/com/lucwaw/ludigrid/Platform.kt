package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


@Composable
expect fun BoxScope.ToolBar(expanded : Boolean, modifier: Modifier = Modifier, share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit)


@Composable
expect fun FAB(expanded : Boolean,share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit)

@Composable
expect fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit)


expect fun Modifier.toolBarModifier(toolbarExpanded: Boolean,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
): Modifier