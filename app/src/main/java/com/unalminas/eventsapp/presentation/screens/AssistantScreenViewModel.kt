package com.unalminas.eventsapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.repository.AssistantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssistantScreenViewModel @Inject constructor(
    private val assistantRepository: AssistantRepository
) : ViewModel() {

    private val _assistantListState = MutableStateFlow(emptyList<Assistant>())
    val assistantListState = _assistantListState.asStateFlow()

    fun getAssistantList() {
        viewModelScope.launch(Dispatchers.IO) {
            _assistantListState.value = assistantRepository.getAssistantList()
        }
    }
}