package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.data.AssistantDataSource
import com.unalminas.eventsapp.domain.Assistant

class AssistantRepositoryImpl(
    private val assistantDataSource: AssistantDataSource,
) : AssistantRepository {

    override suspend fun getAssistantList(): List<Assistant> {
        return assistantDataSource.getAssistantsList()
    }

    override suspend fun deleteAssistantById(id: Int) {
       assistantDataSource.deleteAssistantById(id)
    }

    override suspend fun getAssistantById(id: Int): Assistant {
       return assistantDataSource.getAssistantById(id)
    }

    override suspend fun updateAssistant(assistant: Assistant) {
        assistantDataSource.updateAssistantById(assistant)
    }

    override suspend fun insertAssistant(assistant: Assistant) {
        assistantDataSource.saveAssistant(assistant)
    }
}