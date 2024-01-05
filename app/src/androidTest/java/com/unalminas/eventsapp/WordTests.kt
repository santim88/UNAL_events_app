package com.unalminas.eventsapp

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.dao.ImageDao
import com.unalminas.eventsapp.framework.db.database.AssistantDataBase
import com.unalminas.eventsapp.framework.db.entity.AssistantEntity
import com.unalminas.eventsapp.framework.db.entity.EventEntity
import com.unalminas.eventsapp.framework.db.entity.ImageEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordTests {

    private lateinit var dataBase: AssistantDataBase
    private lateinit var assistantDao: AssistantDao
    private lateinit var eventDao: EventDao
    private lateinit var imageDao: ImageDao

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AssistantDataBase::class.java
        ).build()

        assistantDao = dataBase.assistantDao()
        eventDao = dataBase.eventDao()
        imageDao = dataBase.imageDao()

    }

    @Test
    fun getAssistantsFromEvent() = runBlocking {
        val event = EventEntity(id = 1, "Test event", "Anything", "M3", null, null)
        eventDao.insertAll(event)
        assert(eventDao.getEventById(1) == event)

        val image1 = ImageEntity(10, 1, byteArrayOf(0, 0, 0))
        val image2 = ImageEntity(11, 1, byteArrayOf(0, 0, 1))
        imageDao.insertImage(image1)
        imageDao.insertImage(image2)
        val imagenesRecuperadas = imageDao.getImagesByEventId(1)
        Log.e("test", "$imagenesRecuperadas")
        assert(imagenesRecuperadas.size == 2)

    }


    @After
    fun tearDown() {
        dataBase.close()
    }
}
