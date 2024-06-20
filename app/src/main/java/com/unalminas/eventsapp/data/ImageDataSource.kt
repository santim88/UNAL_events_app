package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Image

interface ImageDataSource {

    suspend fun saveImage(image: Image)
    suspend fun getImagesById(id: Int): Image
    suspend fun getAllImages(): List<Image>
    suspend fun getImagesByEventId(eventId: Int): List<Image>
    suspend fun deleteAllImages()
    suspend fun deleteImageById(id: Int)
}