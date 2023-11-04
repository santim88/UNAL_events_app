package com.example.pokedex.data

import com.example.pokedex.domain.Event

interface EventDataSource {

    suspend fun getEventList(): List<Event>

    fun saveEvent(event: Event)
}
