package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.toEvent
import com.unalminas.eventsapp.framework.db.toEventEntity
import javax.inject.Inject

class EventDataSourceImpl @Inject constructor(
    private val eventDao: EventDao
) : EventDataSource {

    override suspend fun getEventList(): List<Event> = eventDao.getAll().map {
        it.toEvent()
    }

    override fun saveEvent(event: Event) {
        eventDao.insertAll(
            event.toEventEntity()
        )
    }

    override suspend fun deleteEventById(eventId: Int) {
        eventDao.deleteEventById(eventId)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event.toEventEntity())
    }

    override suspend fun updateEvent(event: Event) {
        eventDao.update(event.toEventEntity())
    }


    override suspend fun getEventById(id: Int): Event {
        val eventEntity = eventDao.getEventById(id)
        return eventEntity.toEvent()
    }
}
