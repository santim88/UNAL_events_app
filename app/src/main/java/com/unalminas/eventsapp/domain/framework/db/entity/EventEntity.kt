package com.unalminas.eventsapp.domain.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "hour") val hour: String?,
)
