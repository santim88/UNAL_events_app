package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.data.AssistantDataSource
import com.unalminas.eventsapp.domain.Assistant

class AssistantRepositoryImpl(
    private val assistantDataSource: AssistantDataSource,
) : AssistantRepository {

    override suspend fun getAssistantList(): List<Assistant> {
        return assistantDataSource.getAssistantsList()
    }

    override suspend fun getAssistantListByEvent(eventId: Int): List<Assistant> {
        return assistantDataSource.getAssistantByEventId(eventId)
    }

    override suspend fun deleteAssistantById(id: Int) {
        return assistantDataSource.deleteAssistantById(id)
    }

    override suspend fun getAssistantById(id: Int): Assistant {
        return assistantDataSource.getAssistantById(id)
    }

    override suspend fun updateAssistant(assistant: Assistant) {
        return assistantDataSource.updateAssistantById(assistant)
    }

    override suspend fun insertAssistant(assistant: Assistant) {
        return assistantDataSource.saveAssistant(assistant)
    }
}