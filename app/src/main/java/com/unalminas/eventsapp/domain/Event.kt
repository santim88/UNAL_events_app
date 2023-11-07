package com.unalminas.eventsapp.domain

data class Event(
    val id: Int? = null,
    val name: String,
    val description: String,
    val place: String,
    val date: String,
    val hour: String
)