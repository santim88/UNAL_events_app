package com.example.pokedex.repository

import com.example.pokedex.domain.Event

interface EventRepository {
    suspend fun getEventList(): List<Event>
    suspend fun insertEvent(event: Event)
}
