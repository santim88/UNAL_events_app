package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.domain.Event

interface EventRepository {
    suspend fun getEventList(): List<Event>
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    suspend fun updateEvent(event: Event)
    suspend fun getEventById(id: Int): Event
    suspend fun deleteEventById(id: Int)
    suspend fun getEventsByDate(date: String): List<Event>
    suspend fun getEventsWithAssistantCount(date: String): List<Event>
}
