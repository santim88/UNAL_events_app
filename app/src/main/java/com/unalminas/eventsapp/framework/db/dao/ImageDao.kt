package com.unalminas.eventsapp.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unalminas.eventsapp.framework.db.entity.ImageEntity

@Dao
interface ImageDao {
    @Insert
    suspend fun insertImage(image: ImageEntity)

    @Query("SELECT * FROM images WHERE id = :id")
    fun getImagesById(id: Int): ImageEntity

    @Query("SELECT * FROM images")
    fun getAll(): List<ImageEntity>

    @Query("SELECT * FROM images WHERE eventId = :eventId")
    fun getImagesByEventId(eventId: Int): List<ImageEntity>

    @Query("DELETE FROM images")
    suspend fun deleteAllImages()

    @Query("DELETE FROM images WHERE id= :id")
    suspend fun deleteImageById(id: Int)
}
