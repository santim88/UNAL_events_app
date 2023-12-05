package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.domain.Assistant

interface AssistantRepository {
    suspend fun getAssistantList(): List<Assistant>
    suspend fun insertAssistant(assistant: Assistant)
    suspend fun updateAssistant(assistant: Assistant)
    suspend fun getAssistantById(id: Int): Assistant
    suspend fun deleteAssistantById(id: Int)
}
