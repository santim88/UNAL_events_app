package com.example.pokedex.repository

import com.example.pokedex.data.EventDataSource
import com.example.pokedex.domain.Event

class EventRepositoryImpl(
    private val eventDataSource: EventDataSource
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return eventDataSource.getEventList()
    }
}
