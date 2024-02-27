package com.unalminas.eventsapp.presentation.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.domain.EventFieldEnum
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormEventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    val dateRegex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$".toRegex()

    val hourRegex = "^(0?[1-9]|1[0-2]):([0-5][0-9])\\s?(AM|PM)$".toRegex()

    private val _eventState = MutableStateFlow(Event())
    val eventState = _eventState.asStateFlow()

    private val _isValidDateState = MutableStateFlow(true)
    val isValidDateState = _isValidDateState.asStateFlow()

    private val _isValidHourState = MutableStateFlow(true)
    val isValidHourState = _isValidHourState.asStateFlow()

    private val _isValidPlaceState = MutableStateFlow(true)
    val isValidPlaceState = _isValidPlaceState.asStateFlow()

    private val _isValidNameState = MutableStateFlow(true)
    val isValidNameState = _isValidNameState.asStateFlow()

    fun isValidDate(date: String) {
        _isValidDateState.value = date.matches(dateRegex)
    }

    fun isValidHour(hour: String) {
        _isValidHourState.value = hour.matches(hourRegex)
    }

    fun isValidName(name: String) {
        _isValidNameState.value = name.isNotEmpty()
    }

    fun isValidPlace(place: String) {
        _isValidPlaceState.value = place.isNotEmpty()
    }

    fun areAllValidFields(event: Event): Boolean {
        _isValidNameState.value = event.name.isNotEmpty()
        _isValidPlaceState.value = event.place.isNotEmpty()
        _isValidHourState.value = event.hour.matches(hourRegex)
        _isValidDateState.value = event.date.matches(dateRegex)
        return event.date.matches(dateRegex)
                && event.hour.matches(hourRegex)
                && event.name.isNotEmpty()
                && event.place.isNotEmpty()
    }

    fun insertEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.insertEvent(event)
        }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.updateEvent(event)
        }
    }

    fun getEventById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val event = eventRepository.getEventById(id)
            _eventState.value = event
        }
    }

    // Edit event

    fun editEventField(fieldEnumType: EventFieldEnum, fieldValue: String) {
        _eventState.value = when (fieldEnumType) {
            EventFieldEnum.NAME -> _eventState.value.copy(name = fieldValue)
            EventFieldEnum.DESCRIPTION -> _eventState.value.copy(description = fieldValue)
            EventFieldEnum.PLACE -> _eventState.value.copy(place = fieldValue)
            EventFieldEnum.DATE -> _eventState.value.copy(date = fieldValue)
            EventFieldEnum.HOUR -> _eventState.value.copy(hour = fieldValue)
        }
    }

}

