package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.Event
import com.example.pokedex.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// 4. Presenter/ViewModel
@HiltViewModel
class ScreenMainViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _eventListState = MutableStateFlow(listOf<Event>())
    val eventListState = _eventListState.asStateFlow()

    fun getEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListState.value = eventRepository.getEventList()
        }
    }

}
