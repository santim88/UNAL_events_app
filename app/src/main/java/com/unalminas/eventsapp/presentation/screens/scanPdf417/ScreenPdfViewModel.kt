package com.unalminas.eventsapp.presentation.screens.scanPdf417

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.integration.android.IntentIntegrator
import com.unalminas.eventsapp.domain.Attendant
import com.unalminas.eventsapp.repository.AttendantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenPdfViewModel @Inject constructor(
    private val attendantRepository: AttendantRepository
) : ViewModel() {

    private val _scannedValue = MutableStateFlow<String?>(null)
//    val scannedValue = _scannedValue.asStateFlow()

    private val _lastAttendantId = MutableStateFlow<String?>(null)
    val lastAttendantId = _lastAttendantId.asStateFlow()
    private fun createAttendant(attendant: Attendant) {
        viewModelScope.launch(Dispatchers.IO) {
            attendantRepository.insertAttendant(attendant)
        }
    }

    private fun createAttendantOutId(attendant: Attendant) {
        viewModelScope.launch(Dispatchers.IO) {
            val insertResult = attendantRepository.insertAttendantOutId(attendant)
            if (insertResult.isNotEmpty()) {
                _lastAttendantId.value = insertResult.first().toString()
            }
        }
    }

    fun handleScanResult(resultCode: Int, data: Intent?, eventId: Int?): Boolean {
        val scanResult = IntentIntegrator.parseActivityResult(resultCode, data)
        if (scanResult.contents != null) {
            val result = scanResult.contents
            _scannedValue.value = scanResult.contents
            val identification = result.substring(48, 58)
            val name = result.substring(58, 120)
            eventId?.let { eventId ->
                createAttendantOutId(
                    Attendant(
                        eventId = eventId,
                        name = name,
                        identification = identification
                    )
                )
            }
            return true
        }
        return false
    }
}
