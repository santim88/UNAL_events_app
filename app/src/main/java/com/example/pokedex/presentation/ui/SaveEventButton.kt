package com.example.pokedex.presentation.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.domain.Event

@Composable
fun SaveEventButton(eventDataSource: EventDataSource) {
    Button(onClick = {
        val event = Event(
            null,
            name = "Event 1",
            description = "Event description",
            place = "M2",
            date = "03/02/2023",
            hour = "12:40"
        )
        eventDataSource.saveEvent(event)
    }) {
        Text("Save Event")
    }
}

