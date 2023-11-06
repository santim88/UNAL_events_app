package com.example.pokedex.data

import com.example.pokedex.domain.Event

class EventMockDataSourceImpl {

    private val eventMockList = listOf(
        Event( null,"Pikachu", "ti", "minas", "16/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( null,"Squirtle", "ti", "minas_3", "18/09/09/09", "2:00"),
        Event( null,"Bulbasaur", "ti", "minas_4", "19/09/09/09", "2:00"),
    )

    /*override suspend fun getEventList(): List<Event> = eventMockList

    override fun saveEvent(event: Event) {

    }
*//*
    override suspend fun deleteEventById(eventId: Int) {
        TODO("Not yet implemented")
    }*//*

    override suspend fun deleteEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun updateEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getEventById(id: Int) {
        TODO("Not yet implemented")
    }*/
}
