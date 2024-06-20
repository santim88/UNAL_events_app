package com.unalminas.eventsapp.di

import com.unalminas.eventsapp.data.AttendantDataSource
import com.unalminas.eventsapp.data.AttendantDataSourceImp
import com.unalminas.eventsapp.data.EventDataSource
import com.unalminas.eventsapp.data.EventDataSourceImpl
import com.unalminas.eventsapp.data.ImageDataSource
import com.unalminas.eventsapp.data.ImageDataSourceImpl
import com.unalminas.eventsapp.framework.db.dao.AttendantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.repository.AttendantRepository
import com.unalminas.eventsapp.repository.AttendantRepositoryImpl
import com.unalminas.eventsapp.repository.EventRepository
import com.unalminas.eventsapp.repository.EventRepositoryImpl
import com.unalminas.eventsapp.repository.ImageRepository
import com.unalminas.eventsapp.repository.ImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Named("EventDatasource")
    @Singleton
    @Provides
    fun provideEventDataSource(
        eventDao: EventDao
    ): EventDataSource {
        return EventDataSourceImpl(eventDao)
    }

    @Singleton
    @Provides
    fun provideEventRepository(
        @Named("EventDatasource") eventDataSource: EventDataSource
    ): EventRepository {
        return EventRepositoryImpl(eventDataSource)
    }

    @Named("AttendantDataSource")
    @Singleton
    @Provides
    fun provideAttendantDataSource(
        AttendantDao: AttendantDao
    ): AttendantDataSource {
        return AttendantDataSourceImp(AttendantDao)
    }

    @Singleton
    @Provides
    fun provideAttendantDataRepository(
        @Named("AttendantDataSource") AttendantDataSource: AttendantDataSource
    ): AttendantRepository {
        return AttendantRepositoryImpl(AttendantDataSource)
    }

    @Named("ImageDataSource")
    @Singleton
    @Provides
    fun provideImageDataSource(
        imageDao: ImageDao
    ): ImageDataSource {
        return ImageDataSourceImpl(imageDao)
    }

    @Singleton
    @Provides
    fun provideImageRepository(
        @Named("ImageDataSource") imageDataSource: ImageDataSource
    ): ImageRepository {
        return ImageRepositoryImpl(imageDataSource)
    }
}
