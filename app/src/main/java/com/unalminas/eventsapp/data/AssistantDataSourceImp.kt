package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.toAssistant
import com.unalminas.eventsapp.framework.db.toAssistantEntity
import javax.inject.Inject


class AssistantDataSourceImp @Inject constructor(
    private val assistantDao: AssistantDao
) : AssistantDataSource {
    override suspend fun getAssistantsList(): List<Assistant> = assistantDao.getAll().map {
        it.toAssistant()
    }

    override suspend fun getAssistantById(id: Int): Assistant {
        return assistantDao.getAssistantById(id).toAssistant()
    }

    override suspend fun deleteAssistantById(assistantId: Int) {
        assistantDao.deleteAssistantById(assistantId)
    }

    override suspend fun updateAssistant(assistant: Assistant) {
       assistantDao.update(assistant.toAssistantEntity())
    }

    override suspend fun saveAssistant(assistant: Assistant) {
        assistantDao.insertAssistant(assistant.toAssistantEntity())
    }

    override suspend fun getAssistantByEventId(eventId: Int): List<Assistant> = assistantDao.getAssistantByEventId(eventId).map{
        it.toAssistant()
    }

    override suspend fun insertAssistantOutId(assistant: Assistant): List<Long> {
        return assistantDao.insertAssistantOutId(assistant.toAssistantEntity())
    }
}
