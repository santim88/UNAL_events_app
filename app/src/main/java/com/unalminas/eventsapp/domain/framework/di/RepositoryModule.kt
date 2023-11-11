package com.unalminas.eventsapp.domain.framework.di

import android.content.Context
import com.unalminas.eventsapp.data.EventDataSource
import com.unalminas.eventsapp.data.EventDataSourceImpl
import com.unalminas.eventsapp.data.EventMockDataSourceImpl
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
    @Named("EventMockDatasource")
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
}
