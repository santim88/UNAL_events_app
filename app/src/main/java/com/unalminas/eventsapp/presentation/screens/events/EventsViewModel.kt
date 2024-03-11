package com.unalminas.eventsapp.presentation.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _eventListState = MutableStateFlow(emptyList<Event>())
    val eventListState = _eventListState.asStateFlow()

    fun getEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListState.value = eventRepository.getEventList()
        }
    }

//    fun deleteEvent(event: Event) {
//        viewModelScope.launch(Dispatchers.IO) {
//            eventRepository.deleteEvent(event)
//            _eventListState.value = eventRepository.getEventList()
//        }
//    }

//    fun getEventListSize(): Int {
//        return _eventListState.value.size
//    }

    fun deleteEventById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.deleteEventById(id)
            val updatedList = eventRepository.getEventList()
            withContext(Dispatchers.Main) {
                _eventListState.value = updatedList
            }
        }
    }
}
