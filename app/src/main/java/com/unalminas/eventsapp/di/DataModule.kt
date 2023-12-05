package com.unalminas.eventsapp.di

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.database.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule{

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): EventDatabase{
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java, "events_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesEventDao(eventDatabase: EventDatabase): EventDao{
        return eventDatabase.eventDao()
    }
}