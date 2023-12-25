package com.unalminas.eventsapp;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.unalminas.eventsapp.presentation.screens.scanPdf417.ScreenPdfViewModel;

import javax.inject.Inject;

/**
 * This activity has a margin.
 */
public class SmallCaptureActivity extends CaptureActivity {
    private NavController navController;
    private boolean isFlashOn = false;
    private DecoratedBarcodeView barcodeView;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ScreenPdfViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected DecoratedBarcodeView initializeContent() {

        setContentView(R.layout.capture_small);
        barcodeView = (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        ImageButton activateFlash = findViewById(R.id.activateFlash);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        activateFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        activateFlash.setOnClickListener(v -> {
            if (isFlashOn) {
                barcodeView.setTorchOff();
                activateFlash.setImageResource(R.drawable.baseline_flash_off_24); // Set icon to flash off
            } else {
                barcodeView.setTorchOn();
                activateFlash.setImageResource(R.drawable.baseline_flash_on_24); // Set icon to flash on
            }
            isFlashOn = !isFlashOn;
        });


        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);

    }
}
