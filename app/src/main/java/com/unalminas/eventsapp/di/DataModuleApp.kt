package com.unalminas.eventsapp.di

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.database.AssistantDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModuleApp {

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AssistantDataBase =
        Room.databaseBuilder(
            context,
            AssistantDataBase::class.java, "assistants_db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun providesAssistantDao(database: AssistantDataBase): AssistantDao = database.assistantDao()

    @Provides
    fun providesEventDao(database: AssistantDataBase): EventDao = database.eventDao()
}