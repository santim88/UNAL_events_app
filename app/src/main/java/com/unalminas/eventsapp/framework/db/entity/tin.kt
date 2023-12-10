package com.unalminas.eventsapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/*
@Entity(
    tableName = "assistants",
    foreignKeys = [
        ForeignKey(
            entity = EventEntity::class,
            parentColumns = ["id"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class AssistantEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "identification") val identification: String,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "eventId") val eventId: Int
)*/
