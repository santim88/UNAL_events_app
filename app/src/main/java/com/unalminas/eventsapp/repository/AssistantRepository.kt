package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.domain.Assistant

interface AssistantRepository {
    suspend fun getAssistantList(): List<Assistant>
}