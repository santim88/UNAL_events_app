package com.example.pokedex.repository

import com.example.pokedex.domain.Event

interface EventRepository {
    suspend fun getEventList(): List<Event>
    suspend fun insertEvent(event: Event)
/*    suspend fun deleteEventById(eventId : Int)*/
    suspend fun deleteEvent(event: Event)
    suspend fun updateEvent(event: Event)
    suspend fun getEventById(id: Int): Event
}
