package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.domain.Image

interface ImageRepository {
    suspend fun getImagesList(): List<Image>

    suspend fun insertImage(image: Image)

    suspend fun getImageById(id: Int): Image

    suspend fun getImagesByEventID(eventId: Int): List<Image>
    suspend fun deleteAllImages()
    suspend fun deleteImageByIt(id: Int)
}