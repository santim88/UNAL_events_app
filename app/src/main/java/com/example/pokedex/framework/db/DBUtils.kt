package com.example.pokedex.framework.db

import com.example.pokedex.domain.Event
import com.example.pokedex.framework.db.entity.EventEntity


fun EventEntity.toEvent() = Event(
    id = id ?: 0,
    name = name ?: "",
    description = description,
    place = place ?: "",
    date = date ?: "",
    hour = hour ?: ""
)

fun Event.toEventEntity() = EventEntity(
    id = id,
    name = name,
    description = description,
    place = place,
    date = date,
    hour = hour
)
