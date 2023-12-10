package com.unalminas.eventsapp.di

import android.content.Context
import com.unalminas.eventsapp.data.AssistantDataSource
import com.unalminas.eventsapp.data.AssistantDataSourceImp
import com.unalminas.eventsapp.data.AssistantMockDataSourceImpl
import com.unalminas.eventsapp.data.EventDataSource
import com.unalminas.eventsapp.data.EventDataSourceImpl
import com.unalminas.eventsapp.data.EventMockDataSourceImpl
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.repository.AssistantRepository
import com.unalminas.eventsapp.repository.AssistantRepositoryImpl
import com.unalminas.eventsapp.repository.EventRepository
import com.unalminas.eventsapp.repository.EventRepositoryImpl
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

    @Named("EventMockDataSource")
    @Singleton
    @Provides
    fun provideEventMockDataSource(): EventDataSource {
        return EventMockDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideEventRepository(
        @Named("EventDatasource") eventDataSource: EventDataSource
    ): EventRepository {
        return EventRepositoryImpl(eventDataSource)
    }

    @Named("AssistantMockDataSource")
    @Singleton
    @Provides
    fun assistantMockDataSource(): AssistantDataSource {
        return AssistantMockDataSourceImpl()
    }

    @Named("AssistantDataSource")
    @Singleton
    @Provides
    fun provideAssistantDataSource(
       assistantDao: AssistantDao
    ): AssistantDataSource {
        return AssistantDataSourceImp(assistantDao)
    }

    @Singleton
    @Provides
    fun provideAssistantDataRepository(
        @Named("AssistantDataSource") assistantDataSource: AssistantDataSource
    ): AssistantRepository {
        return AssistantRepositoryImpl(assistantDataSource)
    }
}
