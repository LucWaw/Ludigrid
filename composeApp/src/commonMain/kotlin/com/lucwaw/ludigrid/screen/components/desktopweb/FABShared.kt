package com.lucwaw.ludigrid.screen.components.desktopweb

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun FABSharedImpl(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick
        },
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add A comment")
    }
}