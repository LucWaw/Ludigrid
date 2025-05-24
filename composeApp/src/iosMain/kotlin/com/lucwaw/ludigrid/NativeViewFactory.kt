package com.lucwaw.ludigrid

import platform.UIKit.UIViewController


interface NativeViewFactory {
    fun createToolbarView(
        onShareClick: () -> Unit,
        onDeleteClick: () -> Unit,
        onAddCommentClick: () -> Unit
    ): UIViewController
}