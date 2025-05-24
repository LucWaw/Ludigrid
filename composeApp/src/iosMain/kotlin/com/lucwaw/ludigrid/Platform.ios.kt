package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitViewController
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@Composable
actual fun BoxScope.ToolBar(
    modifier: Modifier,
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    val factory = LocalNativeViewFactory.current
    UIKitViewController(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        factory = {
            factory.createToolbarView(
                onShareClick = { share() },
                onDeleteClick = { delete() },
                onAddCommentClick = { addAComment() }
            )
        }
    )
}

@Composable
actual fun FAB(onClick: () -> Unit) {
    //NOTHING
}

@Composable
actual fun MENU(
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    //NOTHING
}