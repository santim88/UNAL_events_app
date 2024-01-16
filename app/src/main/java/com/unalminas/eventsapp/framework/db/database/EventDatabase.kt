package com.unalminas.eventsapp.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.entity.AssistantEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.ImageEntity

@Database(entities = [AssistantEntity::class, EventEntity::class, ImageEntity::class], version = 7)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
