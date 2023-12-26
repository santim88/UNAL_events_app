package com.unalminas.eventsapp.presentation.screens.scanPdf417


import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.integration.android.IntentIntegrator
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.repository.AssistantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScreenPdfViewModel @Inject constructor(
    private val assistantRepository: AssistantRepository
) : ViewModel() {

    private val _scannedValue = MutableStateFlow<String?>(null)
    val scannedValue = _scannedValue.asStateFlow()

    private val _lastAssistantId = MutableStateFlow<String?>(null)
    val lastAssistantId = _lastAssistantId.asStateFlow()
    private fun createAssistant(assistant: Assistant) {
        viewModelScope.launch(Dispatchers.IO) {
            assistantRepository.insertAssistant(assistant)
        }
    }

    private fun createAssistantOutId(assistant: Assistant) {
        viewModelScope.launch(Dispatchers.IO) {
            val insertResult = assistantRepository.insertAssistantOutId(assistant)
            if (insertResult.isNotEmpty()) {
                _lastAssistantId.value = insertResult.first().toString()
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
                createAssistantOutId(
                    Assistant(
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
