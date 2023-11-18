package com.unalminas.eventsapp.domain.framework.di

import android.content.Context
import com.unalminas.eventsapp.data.AssistantDataSource
import com.unalminas.eventsapp.data.AssistantDataSourceImp
import com.unalminas.eventsapp.data.AssistantMockDataSourceImpl
import com.unalminas.eventsapp.data.EventDataSource
import com.unalminas.eventsapp.data.EventDataSourceImpl
import com.unalminas.eventsapp.data.EventMockDataSourceImpl
import com.unalminas.eventsapp.repository.AssistantRepository
import com.unalminas.eventsapp.repository.AssistantRepositoryImpl
import com.unalminas.eventsapp.repository.EventRepository
import com.unalminas.eventsapp.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context: Context
    ): EventDataSource {
        return EventDataSourceImpl(context)
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
        @ApplicationContext context: Context
    ): AssistantDataSource {
        return AssistantDataSourceImp(context)
    }

    @Singleton
    @Provides
    fun provideAssistantDataRepository(
        @Named("AssistantDataSource") assistantDataSource: AssistantDataSource
    ): AssistantRepository {
        return AssistantRepositoryImpl(assistantDataSource)
    }
}
