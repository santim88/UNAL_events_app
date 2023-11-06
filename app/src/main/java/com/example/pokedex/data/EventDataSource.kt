package com.example.pokedex.data

import com.example.pokedex.domain.Event

interface EventDataSource {

    suspend fun getEventList(): List<Event>

    fun saveEvent(event: Event)

/*    suspend fun deleteEventById(eventId: Int)*/

    suspend fun deleteEvent(event: Event)

    suspend fun updateEvent(event: Event)

    suspend fun getEventById(id: Int): Event
}
