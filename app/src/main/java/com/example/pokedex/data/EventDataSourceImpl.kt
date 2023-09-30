package com.example.pokedex.data

import android.content.Context
import androidx.room.Room
import com.example.pokedex.framework.db.database.EventDatabase
import com.example.pokedex.domain.Event
import com.example.pokedex.framework.db.toEvent
import com.example.pokedex.framework.db.toEventEntity

//Data Source
class EventDataSourceImpl(
    private val applicationContext: Context
) : EventDataSource {

    private val db = Room.databaseBuilder(
        applicationContext,
        EventDatabase::class.java, "events_db"
    ).build()

    private val eventDao = db.EventDao()
    override suspend fun getEventList(): List<Event> = eventDao.getAll().map {
        it.toEvent()
    }

    override fun saveEvent(event: Event) {
        eventDao.insertAll(
            event.toEventEntity()
        )
    }

}
