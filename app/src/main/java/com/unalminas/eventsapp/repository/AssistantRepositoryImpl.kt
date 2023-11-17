package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.data.AssistantDataSource
import com.unalminas.eventsapp.domain.Assistant

class AssistantRepositoryImpl(
    private val assistantDataSource: AssistantDataSource,
) : AssistantRepository {

    override suspend fun getAssistantList(): List<Assistant> {
        return assistantDataSource.getAssistantsList()
    }
}