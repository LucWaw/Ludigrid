package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lucwaw.ludigrid.screen.components.desktopweb.FABSharedImpl
import com.lucwaw.ludigrid.screen.components.desktopweb.MENUSharedImpl

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()


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


actual fun Modifier.toolBarModifier(
    toolbarExpanded: Boolean,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
): Modifier {
    //DO NOTHING
    return this
}