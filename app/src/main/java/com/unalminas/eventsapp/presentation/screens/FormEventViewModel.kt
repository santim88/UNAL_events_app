package com.unalminas.eventsapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Event
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

//    fun editEventField(fieldName: String, fieldValue: String) {
//        _eventState.value = _eventState.value.copy(name = name)
//    }

}

sealed class EditEventState {
//    object Loading : EditEventState()
//    data class Success(val event: Event) : EditEventState()
//    data class Error(val message: String) : EditEventState()
}