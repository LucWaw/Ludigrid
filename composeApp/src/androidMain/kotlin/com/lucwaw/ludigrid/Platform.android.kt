package com.lucwaw.ludigrid

import android.os.Build
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
actual fun BoxScope.ToolBar(modifier: Modifier, share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit){
    var expanded by rememberSaveable { mutableStateOf(true) }

    HorizontalFloatingToolbar(
        expanded = expanded,
        floatingActionButton = {
            FloatingToolbarDefaults.VibrantFloatingActionButton(
                {addAComment()}
            ) { Icon(Icons.Filled.Add,contentDescription = "Add A comment")}
        },
        modifier =
            Modifier.align(Alignment.BottomCenter).padding(bottom = 40.dp)
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
actual fun FAB(onClick : () -> Unit){
    //NOTHING
}

@Composable
actual fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    //NOTHING
}

@Composable
actual fun TakePicture() {
    TODO("Not yet implemented")
}