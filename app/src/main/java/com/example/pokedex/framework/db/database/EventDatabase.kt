package com.example.pokedex.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.framework.db.dao.EventDao
import com.example.pokedex.framework.db.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun EventDao(): EventDao
}
