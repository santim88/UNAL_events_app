package com.unalminas.eventsapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class EventWithAttendanceCount(
    @Embedded val event: EventEntity,
    @ColumnInfo(name = "quantityAttendants") val quantityAttendants: Int
)
