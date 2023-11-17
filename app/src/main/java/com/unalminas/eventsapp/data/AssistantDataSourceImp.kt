package com.unalminas.eventsapp.data

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.domain.framework.db.database.AssistantDataBase
import com.unalminas.eventsapp.domain.framework.db.toAssistant


class AssistantDataSourceImp(
    private val applicationContext: Context
) : AssistantDataSource {

    private val db = Room.databaseBuilder(
        applicationContext,
        AssistantDataBase::class.java, "assistants_db"
    ).fallbackToDestructiveMigration().build()

    private val assistantDao = db.AssistantDao()
    override suspend fun getAssistantsList(): List<Assistant> = assistantDao.getAll().map {
        it.toAssistant()
    }
}
