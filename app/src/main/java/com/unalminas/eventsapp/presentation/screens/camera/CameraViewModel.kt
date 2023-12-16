package com.unalminas.eventsapp.presentation.screens.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalminas.eventsapp.domain.Image
import com.unalminas.eventsapp.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    suspend fun saveImage(image: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.insertImage(image)
            val bitmap = BitmapFactory.decodeByteArray(image.imageByteArray, 0, image.imageByteArray.size)
            _bitmaps.value += bitmap
        }
    }

    suspend fun getImagesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _bitmaps.value = imageRepository.getImagesList().map {
                BitmapFactory.decodeByteArray(it.imageByteArray, 0, it.imageByteArray.size)
            }
        }
    }

    suspend fun deleteAllImages(){
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.deleteAllImages()
        }
    }

    suspend fun getImageById(id: Int): Image {
        return imageRepository.getImageById(id)
    }
}