package com.example.pokedex.repository

import android.content.Context
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.data.EventMockDataSourceImpl
import com.example.pokedex.domain.Event

class EventRepositoryImpl(
    private val context: Context,
    private val eventDataSource: EventDataSource = EventMockDataSourceImpl() //EventDataSourceImpl(context) // TODO: FIX THIS WHIT HILT
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return eventDataSource.getEventList()
    }
}
