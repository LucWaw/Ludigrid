package com.lucwaw.ludigrid.components.desktopweb

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.actions
import ludigrid.composeapp.generated.resources.add_a_comment
import ludigrid.composeapp.generated.resources.delete
import ludigrid.composeapp.generated.resources.share
import org.jetbrains.compose.resources.stringResource

@Composable
fun MENUSharedImpl(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    Box {
        var expanded by remember { mutableStateOf(false) }

        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = stringResource(
                    Res.string.actions
                )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 0.dp, y = 0.dp)
        ) {
            DropdownMenuItem(
                onClick = {
                    share()
                    expanded = false
                },
                text = { Text(stringResource(Res.string.share)) }
            )
            DropdownMenuItem(
                onClick = {
                    delete()
                    expanded = false
                },
                text = { Text(stringResource(Res.string.delete)) }
            )
            DropdownMenuItem(
                onClick = {
                    addAComment()
                    expanded = false
                },
                text = { Text(stringResource(Res.string.add_a_comment)) }
            )
        }
    }
}