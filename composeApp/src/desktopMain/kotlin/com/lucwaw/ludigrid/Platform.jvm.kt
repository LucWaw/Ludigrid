package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lucwaw.ludigrid.screen.components.desktopweb.FABSharedImpl
import com.lucwaw.ludigrid.screen.components.desktopweb.MENUSharedImpl

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun BoxScope.ToolBar(
    expanded: Boolean,
    modifier: Modifier,
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    //Nothing
}

@Composable
actual fun FAB(
    expanded: Boolean,
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    FABSharedImpl(addAComment)
}

@Composable
actual fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    MENUSharedImpl(share, delete, addAComment)
}

actual fun Modifier.toolBarModifier(
    toolbarExpanded: Boolean,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
): Modifier {
    //DO NOTHING
    return this
}