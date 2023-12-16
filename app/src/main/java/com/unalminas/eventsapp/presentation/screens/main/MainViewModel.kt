package com.unalminas.eventsapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.unalminas.eventsapp.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventRepository: EventRepository

) : ViewModel() {

}
