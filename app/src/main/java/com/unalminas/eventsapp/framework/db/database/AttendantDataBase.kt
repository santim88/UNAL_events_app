package com.unalminas.eventsapp.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unalminas.eventsapp.framework.db.dao.AttendantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.framework.db.entity.AttendanceEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.ImageEntity

@Database(entities = [AttendanceEntity::class, EventEntity::class, ImageEntity::class], version = 8)
abstract class AttendantDataBase : RoomDatabase() {
    abstract fun attendantDao(): AttendantDao
    abstract fun eventDao(): EventDao
    abstract fun imageDao(): ImageDao
}
