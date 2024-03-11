package com.unalminas.eventsapp.presentation.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _formattedDateState = MutableStateFlow<String>(getFormattedDate())
    val formattedDataState = _formattedDateState.asStateFlow()

    private val _formattedDateStateOnlyDay = MutableStateFlow<String>(getFormattedDateOnlyDay())
    val formattedDateStateOnlyDay = _formattedDateStateOnlyDay.asStateFlow()

    private val _currentDateState = MutableStateFlow<String>(getCurrentDate())
    val currentDateState = _currentDateState.asStateFlow()

    private val _eventListState = MutableStateFlow(emptyList<Event>())
    val eventListState = _eventListState.asStateFlow()

    private val _eventListWithAssistantCountState = MutableStateFlow(emptyList<Event>())
    val eventListWithAssistantCountState = _eventListWithAssistantCountState.asStateFlow()

    fun setDateSelected(date: String) {
        _formattedDateState.value = date
    }

    fun setDateSelectedOnlyDay(date: String) {
        _formattedDateStateOnlyDay.value = date
    }

    fun setCurrentDate(date: String) {
        _currentDateState.value = date
    }

    fun getEventsByDate(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListState.value = eventRepository.getEventsByDate(date)
        }
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("dd 'de' MMMM", Locale("es", "ES"))
        return format.format(calendar.time)
    }

    private fun getFormattedDateOnlyDay(): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("EEEE", Locale("es", "ES"))
        return format.format(calendar.time)
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(calendar.time)
    }

    fun getEventsWithAssistantCount(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _eventListWithAssistantCountState.value =
                eventRepository.getEventsWithAssistantCount(date)
        }
    }
}