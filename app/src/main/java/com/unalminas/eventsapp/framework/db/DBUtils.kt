package com.unalminas.eventsapp.framework.db

import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.domain.Image
import com.unalminas.eventsapp.framework.db.entity.AssistantEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.ImageEntity


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

fun AssistantEntity.toAssistant() = Assistant(
    id = id,
    name = name,
    identification = identification,
    email = email ?: "",
    eventId = eventId
)

fun Assistant.toAssistantEntity() = AssistantEntity(
    id = id,
    name = name,
    identification = identification,
    email = email,
    eventId = eventId
)

fun ImageEntity.toImage() = Image(
    id = id,
    eventId = eventId,
    imageByteArray = imageByteArray
)

fun Image.toImageEntity() = ImageEntity(
    id = id,
    eventId = eventId,
    imageByteArray = imageByteArray
)