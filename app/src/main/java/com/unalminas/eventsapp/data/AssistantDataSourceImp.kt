package com.unalminas.eventsapp.data

import android.content.Context
import androidx.room.Room
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.framework.db.database.AssistantDataBase
import com.unalminas.eventsapp.framework.db.toAssistant
import com.unalminas.eventsapp.framework.db.toAssistantEntity


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

    override suspend fun getAssistantById(id: Int): Assistant {
        return assistantDao.getAssistantById(id).toAssistant()
    }

    override suspend fun deleteAssistantById(assistantId: Int) {
        assistantDao.deleteAssistantById(assistantId)
    }

    override suspend fun updateAssistantById(assistant: Assistant) {
       assistantDao.update(assistant.toAssistantEntity())
    }

    override suspend fun saveAssistant(assistant: Assistant) {
        assistantDao.insertAssistant(assistant.toAssistantEntity())
    }
}
