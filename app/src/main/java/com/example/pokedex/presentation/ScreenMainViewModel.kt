package com.example.pokedex.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.Event
import com.example.pokedex.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// 4. Presenter/ViewModel
@HiltViewModel
class ScreenMainViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _eventListState = MutableStateFlow(emptyList<Event>())
    val eventListState = _eventListState.asStateFlow()

/*    private val _eventState = MutableStateFlow<Event?>(null)
    val eventState = _eventListState.asStateFlow()*/
    private val _eventState = MutableStateFlow<Event?>(null)
    val eventState = _eventState.asStateFlow()


    fun getEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListState.value = eventRepository.getEventList()
        }
    }

    fun insertEvent(event: Event) {
           viewModelScope.launch (Dispatchers.IO){
               eventRepository.insertEvent(event)
         }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch (Dispatchers.IO){
            eventRepository.deleteEvent(event)
            _eventListState.value = eventRepository.getEventList()
        }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch (Dispatchers.IO){
            eventRepository.updateEvent(event)
        }
    }

    fun getEventById(id: Int ?) {
        viewModelScope.launch (Dispatchers.IO){
            _eventState.value = eventRepository.getEventById(id ?: 0)
        }
    }

    /*    fun deleteEventById(eventID: Int) {
        viewModelScope.launch (Dispatchers.IO){
            eventRepository.deleteEventById(eventID)
        }
    }*/

    /* private val _eventListState = MutableStateFlow(listOf<Event>())
   val eventListState = _eventListState.asStateFlow()*/
}
