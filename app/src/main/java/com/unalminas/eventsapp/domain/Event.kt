package com.unalminas.eventsapp.domain

import com.unalminas.eventsapp.framework.db.entity.EventWithAssistantCount

data class Event(
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String = "",
    val description: String = "",
    val place: String = "",
    var date: String = "",
    val hour: String = "",
    val assistantCount: Int = 0
)
