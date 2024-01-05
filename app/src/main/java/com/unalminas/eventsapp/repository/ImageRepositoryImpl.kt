package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.data.ImageDataSource
import com.unalminas.eventsapp.domain.Image

class ImageRepositoryImpl(
    private val imageDataSource: ImageDataSource
) : ImageRepository{
    override suspend fun getImageById(id: Int): Image {
        return imageDataSource.getImagesById(id)
    }

    override suspend fun getImagesList(): List<Image> {
        return imageDataSource.getAllImages()
    }

    override suspend fun insertImage(image: Image) {
        imageDataSource.saveImage(image)
    }

    override suspend fun getImagesByEventID(eventId: Int): List<Image> {
        return imageDataSource.getImagesByEventId(eventId)
    }

    override suspend fun deleteAllImages() {
        imageDataSource.deleteAllImages()
    }

    override suspend fun deleteImageByIt(id: Int) {
        imageDataSource.deleteImageById(id)
    }
}