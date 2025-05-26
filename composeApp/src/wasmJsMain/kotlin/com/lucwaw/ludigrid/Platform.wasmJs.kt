package com.lucwaw.ludigrid

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lucwaw.ludigrid.components.desktopweb.FABSharedImpl
import com.lucwaw.ludigrid.components.desktopweb.MENUSharedImpl
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.mediacapture.MediaStreamConstraints

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()


@Composable
actual fun FAB(onClick: () -> Unit) {
    FABSharedImpl(onClick)
}

@Composable
actual fun MENU(share: () -> Unit, delete: () -> Unit, addAComment: () -> Unit) {
    MENUSharedImpl(share, delete, addAComment)
}

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
actual fun TakePicture() {


}

fun getConstraints(): MediaStreamConstraints =
    MediaStreamConstraints(video = true.toJsBoolean(), audio = false.toJsBoolean())


fun startup() {

    val width = 320
    var height = 0
    var streaming = false

    val video = document.getElementById("video") as? HTMLVideoElement ?: return
    val canvas = document.getElementById("canvas") as? HTMLCanvasElement ?: return
    val photo = document.getElementById("photo") as? HTMLImageElement ?: return
    val startButton = document.getElementById("start-button") as? HTMLButtonElement ?: return

    val mediaDevices = window.navigator.mediaDevices

    (window.navigator.getUserMedia(
        MediaStreamConstraints(video = true.toJsBoolean(), audio = false.toJsBoolean()),
        successCallback = {
            video.srcObject = it
            video.play()
        },
        errorCallback = {
            println("Issue is $it")
        }))
    /*

        video.addEventListener("canplay", {
            if (!streaming) {
                height = (video.videoHeight / (video.videoWidth / width.toDouble())).toInt()

                video.setAttribute("width", width.toString())
                video.setAttribute("height", height.toString())
                canvas.setAttribute("width", width.toString())
                canvas.setAttribute("height", height.toString())
                streaming = true
            }
        })

        startButton.addEventListener("click", {
            takePicture(video, canvas, photo, width, height)
        })

        clearPhoto(canvas, photo)*/
}

fun clearPhoto(canvas: HTMLCanvasElement, photo: HTMLImageElement) {
    val context = canvas.getContext("2d") as? CanvasRenderingContext2D ?: return
    context.fillStyle = "#AAA".toJsString()
    context.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())

    val data = canvas.toDataURL("image/png")
    photo.setAttribute("src", data)
}

fun takePicture(
    video: HTMLVideoElement,
    canvas: HTMLCanvasElement,
    photo: HTMLImageElement,
    width: Int,
    height: Int
) {
    val context = canvas.getContext("2d") as? CanvasRenderingContext2D ?: return
    if (width != 0 && height != 0) {
        canvas.width = width
        canvas.height = height
        context.drawImage(video, 0.0, 0.0, width.toDouble(), height.toDouble())

        val data = canvas.toDataURL("image/png")
        photo.setAttribute("src", data)
    } else {
        clearPhoto(canvas, photo)
    }
}
