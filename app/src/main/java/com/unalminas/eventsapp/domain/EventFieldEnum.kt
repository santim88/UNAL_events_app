package com.unalminas.eventsapp.domain

enum class EventFieldEnum(
    val fieldName: String
) {
    NAME("name"),
    DESCRIPTION("description"),
    PLACE("place"),
    DATE("date"),
    HOUR("hour")
}
