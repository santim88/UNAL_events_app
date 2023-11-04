package com.example.pokedex.repository

import com.example.pokedex.data.EventDataSource
import com.example.pokedex.data.EventDataSourceImpl
import com.example.pokedex.domain.Event
import com.example.pokedex.framework.db.dao.EventDao

class EventRepositoryImpl(
    private val eventDataSource: EventDataSource,
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return eventDataSource.getEventList()
    }

    override suspend fun insertEvent(event: Event) {
        return  eventDataSource.saveEvent(event)
    }
}