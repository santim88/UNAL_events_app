package com.unalminas.eventsapp.presentation.screens.assistants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.domain.AssistantFieldEnum
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.repository.AssistantRepository
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssistantScreenViewModel @Inject constructor(
    private val assistantRepository: AssistantRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()

    private val identificationRegex = "^\\d{1,18}\$".toRegex()

    private val _assistantListState = MutableStateFlow(emptyList<Assistant>())
    val assistantListState = _assistantListState.asStateFlow()

    private val _assistantState = MutableStateFlow(Assistant())
    val assistantState = _assistantState.asStateFlow()

    private val _isValidNameState = MutableStateFlow(true)
    val isValidNameState = _isValidNameState.asStateFlow()

    private val _isValidIdentificationState = MutableStateFlow(true)
    val isValidIdentificationState = _isValidIdentificationState.asStateFlow()

    private val _isValidEmailState = MutableStateFlow(true)
    val isValidEmailState = _isValidEmailState.asStateFlow()

    fun isValidName(name: String) {
        _isValidNameState.value = name.isNotEmpty()
    }

    fun isValidIdentification(identification: String) {
        _isValidIdentificationState.value = identification.matches(identificationRegex)
    }

    fun isValidEmail(email: String) {
        _isValidEmailState.value = email.matches(emailRegex)
    }

    fun areAllValidFields(assistant: Assistant): Boolean {
        _isValidNameState.value = assistant.name.isNotEmpty()
        _isValidIdentificationState.value = assistant.identification.matches(identificationRegex)
        _isValidEmailState.value = assistant.email.matches(emailRegex)
        return assistant.name.isNotEmpty()
                && assistant.identification.matches(identificationRegex)
                && assistant.email.matches(emailRegex)
    }

    private val _eventCurrentState = MutableStateFlow(Event())
    val eventCurrentState = _eventCurrentState.asStateFlow()

    fun getAssistantList() {
        viewModelScope.launch(Dispatchers.IO) {
            _assistantListState.value = assistantRepository.getAssistantList()
        }
    }

    fun getAssistantListByEvent(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _assistantListState.value = assistantRepository.getAssistantListByEvent(eventId)
        }
    }

    fun deleteAssistantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            assistantRepository.deleteAssistantById(id)
            _assistantListState.value = assistantRepository.getAssistantList()
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

    fun editAssistantField(fieldEnumType: AssistantFieldEnum, fieldValue: String) {
        _assistantState.value = when (fieldEnumType) {
            AssistantFieldEnum.NAME -> _assistantState.value.copy(name = fieldValue)
            AssistantFieldEnum.IDENTIFICATION -> _assistantState.value.copy(identification = fieldValue)
            AssistantFieldEnum.EMAIL -> _assistantState.value.copy(email = fieldValue)
            AssistantFieldEnum.EVENTID -> _assistantState.value.copy(eventId = fieldValue.toInt())
        }
    }

    fun getEventById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val event = eventRepository.getEventById(id)
            _eventCurrentState.value = event
        }
    }
}