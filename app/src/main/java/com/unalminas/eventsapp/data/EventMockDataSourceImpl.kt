package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Event

class EventMockDataSourceImpl : EventDataSource{

    private val eventMockList = listOf(
        Event( name= "Pikachu", description =  "ti", place =  "minas", date =  "16/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Charmander", description =  "ti", place =  "minas_2", date =  "17/09/09/09", hour =  "2:00"),
        Event( name= "Squirtle", description =  "ti", place =  "minas_3", date =  "18/09/09/09", hour =  "2:00"),
        Event( name= "Bulbasaur", description =  "ti", place =  "minas_4", date =  "19/09/09/09", hour =  "2:00"),
    )

    override suspend fun getEventList(): List<Event> = eventMockList

    override fun saveEvent(event: Event) {

    }

    override suspend fun deleteEventById(eventId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun updateEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getEventById(id: Int): Event {
        TODO("Not yet implemented")
    }

    override suspend fun getEventsByDate(date: String): List<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventsWithAssistantCount(date: String): List<Event> {
        TODO("Not yet implemented")
    }
}
