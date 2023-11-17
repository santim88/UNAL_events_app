package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Assistant

interface AssistantDataSource {

    suspend fun getAssistantsList(): List<Assistant>

}