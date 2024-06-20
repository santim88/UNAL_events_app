package com.unalminas.eventsapp.domain

data class Event(
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String = "",
    val description: String = "",
    val place: String = "",
    var date: String = "",
    val hour: String = "",
    val attendantCount: Int = 0
)
