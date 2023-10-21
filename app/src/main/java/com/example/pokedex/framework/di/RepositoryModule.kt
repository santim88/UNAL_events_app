package com.example.pokedex.framework.di

import android.content.Context
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.data.EventDataSourceImpl
import com.example.pokedex.data.EventMockDataSourceImpl
import com.example.pokedex.repository.EventRepository
import com.example.pokedex.repository.EventRepositoryImpl
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
        @Named("EventMockDatasource") eventDataSource: EventDataSource
    ): EventRepository {
        return EventRepositoryImpl(eventDataSource)
    }

}
