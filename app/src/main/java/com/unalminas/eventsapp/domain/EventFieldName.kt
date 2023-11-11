package com.unalminas.eventsapp.domain

enum class EventFieldName(
    val fieldName: String
) {
    NAME("name"),
    DESCRIPTION("description"),
    PLACE("place"),
    DATE("date"),
    HOUR("hour")
}
