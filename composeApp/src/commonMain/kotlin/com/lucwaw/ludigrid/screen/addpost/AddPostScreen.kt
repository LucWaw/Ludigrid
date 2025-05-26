package com.lucwaw.ludigrid.screen.addpost

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil3.compose.SubcomposeAsyncImage
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.readBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.error_image
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddPostScreen() {

    //Saving image is not alowed on browser so we save a temp val then but it
    // in firestore when user click on validate post button
    val imageTemp = remember { mutableStateOf<ByteArray?>(null) }

    val launcher = rememberFilePickerLauncher(type = FileKitType.Image) { image ->
        CoroutineScope(Dispatchers.Default).launch {
            val bytes = image?.readBytes()
            withContext(Dispatchers.Main) {
                imageTemp.value = bytes
            }
        }
    }

    if (imageTemp.value != null) {
        SubcomposeAsyncImage(
            model = imageTemp.value,
            contentDescription = "Post Image",
            contentScale = ContentScale.Crop,
            error = {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = ColorPainter(Color.Red),
                        contentDescription = "Error",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.error_image),
                        contentDescription = "Error Image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        )
    }


    Button(onClick = { launcher.launch() }) {
        Text("Pick a file")
    }
}