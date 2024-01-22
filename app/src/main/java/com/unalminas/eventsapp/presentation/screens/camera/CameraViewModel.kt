package com.unalminas.eventsapp.presentation.screens.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableStateListOf
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
    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }

    private val _allBitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val allBitmaps = _allBitmaps.asStateFlow()

    private val _bitmapsByEvent = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmapsByEvent = _bitmapsByEvent.asStateFlow()



    suspend fun saveImage(image: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.insertImage(image)
            val bitmap = BitmapFactory.decodeByteArray(image.imageByteArray, 0, image.imageByteArray.size)
            _bitmapsByEvent.value += bitmap
        }
    }

    suspend fun getImagesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _allBitmaps.value = imageRepository.getImagesList().map {
                BitmapFactory.decodeByteArray(it.imageByteArray, 0, it.imageByteArray.size)
            }
        }
    }

    suspend fun getImagesListByEventId(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _bitmapsByEvent.value = imageRepository.getImagesByEventID(eventId).map {
                BitmapFactory.decodeByteArray(it.imageByteArray, 0, it.imageByteArray.size)

            }
        }
    }

    suspend fun deleteAllImages(){
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.deleteAllImages()
        }
    }
//    suspend fun deleteImageById(id: Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            imageRepository.deleteImageByIt(id)
//        }
//    }

    suspend fun getImageById(id: Int): Image {
        return imageRepository.getImageById(id)
    }
}