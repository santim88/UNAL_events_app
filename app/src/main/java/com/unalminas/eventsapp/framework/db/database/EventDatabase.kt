package com.unalminas.eventsapp.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.entity.EventEntity

@Database(entities = [EventEntity::class], version = 6)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
