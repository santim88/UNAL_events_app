package com.unalminas.eventsapp.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageByteArray: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageEntity

        if (id != other.id) return false
        if (!imageByteArray.contentEquals(other.imageByteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + imageByteArray.contentHashCode()
        return result
    }
}