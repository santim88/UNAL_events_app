package com.unalminas.eventsapp.presentation.screens.Attendants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Attendant
import com.unalminas.eventsapp.domain.AttendantFieldEnum
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.repository.AttendantRepository
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendantScreenViewModel @Inject constructor(
    private val attendantRepository: AttendantRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()

    private val identificationRegex = "^\\d{1,18}\$".toRegex()

    private val _attendantListState = MutableStateFlow(emptyList<Attendant>())
    val attendantListState = _attendantListState.asStateFlow()

    private val _attendantState = MutableStateFlow(Attendant())
    val attendantState = _attendantState.asStateFlow()

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

    fun areAllValidFields(attendant: Attendant): Boolean {
        _isValidNameState.value = attendant.name.isNotEmpty()
        _isValidIdentificationState.value = attendant.identification.matches(identificationRegex)
        _isValidEmailState.value = attendant.email.matches(emailRegex)
        return attendant.name.isNotEmpty()
                && attendant.identification.matches(identificationRegex)
                && attendant.email.matches(emailRegex)
    }

    private val _eventCurrentState = MutableStateFlow(Event())
    val eventCurrentState = _eventCurrentState.asStateFlow()

    fun getAttendantList() {
        viewModelScope.launch(Dispatchers.IO) {
            _attendantListState.value = attendantRepository.getAttendantList()
        }
    }

    fun getAttendantListByEvent(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _attendantListState.value = attendantRepository.getAttendantListByEvent(eventId)
        }
    }

    fun deleteAttendantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            attendantRepository.deleteAttendantById(id)
            _attendantListState.value = attendantRepository.getAttendantList()
        }
    }

    fun getAttendantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val attendant = attendantRepository.getAttendantById(id)
            _attendantState.value = attendant
        }
    }

    fun updateAttendant(attendant: Attendant) {
        viewModelScope.launch(Dispatchers.IO) {
            attendantRepository.updateAttendant(attendant)
        }
    }

    fun createAttendant(attendant: Attendant) {
        viewModelScope.launch(Dispatchers.IO) {
            attendantRepository.insertAttendant(attendant)
        }
    }

    fun editAttendantField(fieldEnumType: AttendantFieldEnum, fieldValue: String) {
        _attendantState.value = when (fieldEnumType) {
            AttendantFieldEnum.NAME -> _attendantState.value.copy(name = fieldValue)
            AttendantFieldEnum.IDENTIFICATION -> _attendantState.value.copy(identification = fieldValue)
            AttendantFieldEnum.EMAIL -> _attendantState.value.copy(email = fieldValue)
            AttendantFieldEnum.EVENTID -> _attendantState.value.copy(eventId = fieldValue.toInt())
        }
    }

    fun getEventById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val event = eventRepository.getEventById(id)
            _eventCurrentState.value = event
        }
    }
}