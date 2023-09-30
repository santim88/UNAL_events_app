package com.example.pokedex.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.Event
import com.example.pokedex.repository.EventRepository
import com.example.pokedex.repository.EventRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// 4. Presenter/ViewModel
class EventViewModel : ViewModel() {

    // TODO: FIX THIS WITH HILT
    private var eventRepository: EventRepository? = null

    private val _eventListState = MutableStateFlow(listOf<Event>())
    val eventListState = _eventListState.asStateFlow()

    fun getEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListState.value = eventRepository?.getEventList() ?: listOf()
        }
    }

    fun initDependencies(context: Context) {
        eventRepository = EventRepositoryImpl(context)
    }

}
