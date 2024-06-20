package com.unalminas.eventsapp.di

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.framework.db.dao.AttendantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.framework.db.database.AttendantDataBase
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
    fun providesDataBase(@ApplicationContext context: Context): AttendantDataBase =
        Room.databaseBuilder(
            context,
            AttendantDataBase::class.java, "attendant_db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun providesAttendantDao(database: AttendantDataBase): AttendantDao = database.attendantDao()

    @Provides
    @Singleton
    fun providesEventDao(database: AttendantDataBase): EventDao = database.eventDao()

    @Provides
    @Singleton
    fun providesImageDao(database: AttendantDataBase): ImageDao = database.imageDao()
}
