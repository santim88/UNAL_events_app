package com.unalminas.eventsapp.domain

data class Image(
    val id: Int = System.currentTimeMillis().hashCode(),
    val eventId: Int = 0,
    val imageByteArray: ByteArray = byteArrayOf(0, 0, 0)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

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