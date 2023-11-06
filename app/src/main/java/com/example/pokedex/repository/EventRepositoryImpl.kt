package com.example.pokedex.repository

import com.example.pokedex.data.EventDataSource
import com.example.pokedex.domain.Event

class EventRepositoryImpl(
    private val eventDataSource: EventDataSource,
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return eventDataSource.getEventList()
    }

    override suspend fun insertEvent(event: Event) {
        return  eventDataSource.saveEvent(event)
    }

    override suspend fun updateEvent(event: Event) {
        return  eventDataSource.updateEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        return  eventDataSource.deleteEvent(event)
    }

    override suspend fun getEventById(id: Int): Event {
       return  eventDataSource.getEventById(id)
    }
}