package com.unalminas.eventsapp.data

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.framework.db.database.EventDatabase
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.framework.db.toEvent
import com.unalminas.eventsapp.framework.db.toEventEntity

class EventDataSourceImpl(
    private val applicationContext: Context
) : EventDataSource {

    private val db = Room.databaseBuilder(
        applicationContext,
        EventDatabase::class.java, "events_db"
    ).fallbackToDestructiveMigration().build()


    private val eventDao = db.EventDao()
    override suspend fun getEventList(): List<Event> = eventDao.getAll().map {
        it.toEvent()
    }

    override fun saveEvent(event: Event) {
        eventDao.insertAll(
            event.toEventEntity()
        )
    }

    override  suspend  fun deleteEventById(eventId: Int) {
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