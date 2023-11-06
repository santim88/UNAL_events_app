package com.example.pokedex.domain

data class Event(
    val id: Int = -1,
    val name: String,
    val description: String,
    val place: String,
    val date: String,
    val hour: String
)