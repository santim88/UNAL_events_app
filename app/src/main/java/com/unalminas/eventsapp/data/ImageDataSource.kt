package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Image
import com.unalminas.eventsapp.framework.db.entity.ImageEntity

interface ImageDataSource {

    suspend fun saveImage(image: Image)
    suspend fun getImagesById(id: Int): Image
    suspend fun getAllImages():List<Image>
    suspend fun deleteAllImages()
}