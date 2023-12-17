package com.unalminas.eventsapp.presentation.screens.camera

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                setupAutoFocus(controller)//can be remplace
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
        modifier = modifier
    )
}

private fun setupAutoFocus(controller: LifecycleCameraController) {
    controller.cameraControl?.let { cameraControl ->
        cameraControl.enableTorch(true)  // Example method call, replace with actual autofocus setup
        cameraControl.setLinearZoom(0.5f) // Example method call, replace with actual autofocus setup
    }
}
