package com.unalminas.eventsapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class EventWithAssistantCount(
    @Embedded val event: EventEntity,
    @ColumnInfo(name = "quantityAssistants") val quantityAssistants: Int
)