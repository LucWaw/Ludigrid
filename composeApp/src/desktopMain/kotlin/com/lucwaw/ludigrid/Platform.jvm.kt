package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lucwaw.ludigrid.components.desktopweb.FABSharedImpl
import com.lucwaw.ludigrid.components.desktopweb.MENUSharedImpl

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun BoxScope.ToolBar(
    modifier: Modifier,
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    //Nothing
}

@Composable
actual fun FAB(onClick : () -> Unit){
    FABSharedImpl(onClick)
}

@Composable
actual fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    MENUSharedImpl(share, delete, addAComment)
}

@Composable
actual fun TakePicture() {
    TODO("Not yet implemented")
}