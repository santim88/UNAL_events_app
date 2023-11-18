package com.unalminas.eventsapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.domain.AssistantFieldEnum
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

    private val _assistantState = MutableStateFlow(Assistant())
    val assistantState = _assistantState.asStateFlow()

    fun getAssistantList() {
        viewModelScope.launch(Dispatchers.IO) {
            _assistantListState.value = assistantRepository.getAssistantList()
        }
    }

    fun deleteAssistantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            assistantRepository.deleteAssistantById(id)
        }
    }

    fun getAssistantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val assistant = assistantRepository.getAssistantById(id)
            _assistantState.value = assistant
        }
    }

    fun updateAssistant(assistant: Assistant) {
        viewModelScope.launch(Dispatchers.IO) {
            assistantRepository.updateAssistant(assistant)
        }
    }

    fun createAssistant(assistant: Assistant) {
        viewModelScope.launch(Dispatchers.IO) {
             assistantRepository.insertAssistant(assistant)
        }
    }

    fun editAssistantField(fieldEnumType: AssistantFieldEnum, fieldValue: String){
        _assistantState.value = when(fieldEnumType){
            AssistantFieldEnum.NAME -> _assistantState.value.copy(name = fieldValue)
            AssistantFieldEnum.IDENTIFICATION -> _assistantState.value.copy(identification = fieldValue)
            AssistantFieldEnum.EMAIL -> _assistantState.value.copy(email = fieldValue)
        }
    }

}