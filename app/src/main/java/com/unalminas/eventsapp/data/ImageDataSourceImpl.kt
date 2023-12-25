package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Image
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.framework.db.toImage
import com.unalminas.eventsapp.framework.db.toImageEntity
import javax.inject.Inject

class ImageDataSourceImpl(
    private val imageDao: ImageDao
) : ImageDataSource {
    override suspend fun getAllImages(): List<Image> = imageDao.getAll().map {
        it.toImage()
    }

    override suspend fun saveImage(image: Image) {
        imageDao.insertImage(image.toImageEntity())
    }

    override suspend fun getImagesById(id: Int): Image {
       return imageDao.getImagesById(id).toImage()
    }

    override suspend fun deleteAllImages() {
        imageDao.deleteAllImages()
    }
}