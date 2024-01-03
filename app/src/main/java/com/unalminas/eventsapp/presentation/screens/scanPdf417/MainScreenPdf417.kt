package com.unalminas.eventsapp.presentation.screens.scanPdf417

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.unalminas.eventsapp.SmallCaptureActivity
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unalminas.eventsapp.presentation.Screen

@Composable
fun MainScreenPdf417(
    navController: NavController,
    eventId: Int?,
    viewModel: ScreenPdfViewModel = hiltViewModel()
) {

    val scannedValue by viewModel.scannedValue.collectAsState()
    val idAssistant by viewModel.lastAssistantId.collectAsState()

    val context = LocalContext.current
    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (viewModel.handleScanResult(result.resultCode, result.data, eventId)) {
            idAssistant?.let {
                val screen = Screen.EditAssistantScreen(it)
                navController.navigate(screen.createRoute())
            }
        }
    }

    LaunchedEffect(idAssistant) {
        idAssistant?.let {
            val screen = Screen.EditAssistantScreen(it)
            navController.navigate(screen.createRoute())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { initiateScanner(context, scannerLauncher, false) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Scan Barcode", fontSize = 18.sp)
        }

        /*Spacer(modifier = Modifier.height(16.dp))

        scannedValue?.let { it ->
            Text("El valor escaneado es: $it", fontSize = 16.sp)
        }*/
    }
}

fun initiateScanner(
    context: Context,
    launcher: ActivityResultLauncher<Intent>,
    isFlashInitiallyOn: Boolean
) {
    val integrator = IntentIntegrator(context as Activity)
    integrator.captureActivity = SmallCaptureActivity::class.java
    integrator.setDesiredBarcodeFormats(IntentIntegrator.PDF_417)
    integrator.setPrompt("Poner la cedula en marco")
    integrator.setOrientationLocked(false)
    integrator.setTorchEnabled(isFlashInitiallyOn)//change the flash
    integrator.setBeepEnabled(true)
    integrator.addExtra("TRY_HARDER", true)
    integrator.addExtra(Intents.Scan.PDF417_MODE, true)
    integrator.addExtra("SCAN_CAMERA_ID", 0)
    val intent = integrator.createScanIntent()
    launcher.launch(intent)
}