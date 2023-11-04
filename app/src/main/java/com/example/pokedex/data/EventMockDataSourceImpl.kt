package com.example.pokedex.data

import com.example.pokedex.domain.Event

class EventMockDataSourceImpl : EventDataSource {

    private val eventMockList = listOf(
        Event( "Pikachu", "ti", "minas", "16/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event( "Squirtle", "ti", "minas_3", "18/09/09/09", "2:00"),
        Event( "Bulbasaur", "ti", "minas_4", "19/09/09/09", "2:00"),
    )

    override suspend fun getEventList(): List<Event> = eventMockList

    override fun saveEvent(event: Event) {

    }

}
