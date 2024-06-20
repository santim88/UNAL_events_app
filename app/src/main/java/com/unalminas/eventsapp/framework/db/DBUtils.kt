package com.unalminas.eventsapp.framework.db

import com.unalminas.eventsapp.domain.Attendant
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.domain.Image
import com.unalminas.eventsapp.framework.db.entity.AttendanceEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.EventWithAttendanceCount
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

fun AttendanceEntity.toAttendant() = Attendant(
    id = id,
    name = name,
    identification = identification,
    email = email ?: "",
    eventId = eventId
)

fun Attendant.toAttendantEntity() = AttendanceEntity(
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

fun EventWithAttendanceCount.toEvent() = Event(
    id = event.id,
    name = event.name ?: "",
    description = event.description,
    place = event.place ?: "",
    date = event.date ?: "",
    hour = event.hour ?: "",
    attendantCount = quantityAttendants
)
