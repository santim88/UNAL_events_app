package com.unalminas.eventsapp.framework.db

import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.framework.db.entity.EventEntity


fun EventEntity.toEvent() = Event(
    id = id,
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
