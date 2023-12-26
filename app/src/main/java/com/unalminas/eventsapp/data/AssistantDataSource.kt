package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Assistant

interface AssistantDataSource {

    suspend fun getAssistantsList(): List<Assistant>

    suspend fun deleteAssistantById(assistantId: Int)

    suspend fun updateAssistantById(assistant: Assistant)

    suspend fun getAssistantById(id: Int): Assistant

    suspend fun saveAssistant(assistant: Assistant)

    suspend fun getAssistantByEventId(id: Int): List<Assistant>

    suspend fun insertAssistantOutId(assistant: Assistant): List<Long>
}