package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Assistant

class AssistantMockDataSourceImpl : AssistantDataSource {

    private val assistantList = listOf<Assistant>(
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "valentian", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
    )

    override suspend fun getAssistantsList(): List<Assistant> = assistantList

    override suspend fun deleteAssistantById(assistantId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAssistantById(id: Int): Assistant {
        TODO("Not yet implemented")
    }

    override suspend fun updateAssistantById(assistant: Assistant) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAssistant(assistant: Assistant) {
        TODO("Not yet implemented")
    }
}