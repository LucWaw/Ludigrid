package com.lucwaw.ludigrid

import android.os.Build
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.floatingToolbarVerticalNestedScroll
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
actual fun FAB(
    expanded: Boolean,
    share: () -> Unit,
    delete: () -> Unit,
    addAComment: () -> Unit
) {
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    HorizontalFloatingToolbar(
        expanded = expanded,
        colors = vibrantColors,
        floatingActionButton = {
            FloatingToolbarDefaults.VibrantFloatingActionButton(
                { addAComment() }
            ) { Icon(Icons.Filled.Add, contentDescription = "Add A comment") }
        },
    ) {
        IconButton(onClick = { share() }) {
            Icon(Icons.Filled.Share, contentDescription = "Share The Content")
        }
        IconButton(onClick = { delete() }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete The Post")
        }

    }
}

@Composable
actual fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    //NOTHING
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
actual fun Modifier.toolBarModifier(
    toolbarExpanded: Boolean,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
): Modifier {
    return then(
        Modifier.floatingToolbarVerticalNestedScroll(
        expanded = toolbarExpanded,
        onExpand = { onExpand() },
        onCollapse = { onCollapse() }
    ))
}