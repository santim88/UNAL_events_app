package com.unalminas.eventsapp.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.framework.db.entity.AssistantEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.ImageEntity

@Database(entities = [AssistantEntity::class, EventEntity::class, ImageEntity::class], version = 7)
abstract class AssistantDataBase : RoomDatabase() {
    abstract fun assistantDao(): AssistantDao
    abstract fun eventDao(): EventDao
    abstract fun imageDao(): ImageDao
}