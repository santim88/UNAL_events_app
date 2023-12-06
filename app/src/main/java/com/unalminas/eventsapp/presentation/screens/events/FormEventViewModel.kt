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

    private val _eventState = MutableStateFlow(Event())
    val eventState = _eventState.asStateFlow()

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
