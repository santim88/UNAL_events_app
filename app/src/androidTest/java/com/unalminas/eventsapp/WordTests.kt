package com.unalminas.eventsapp

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

        val byteArray = byteArrayOf(1, 2, 3) // Replace this with your actual ByteArray
        val imageEntity = ImageEntity(id = 1, imageByteArray = byteArray)
        val assitantTest: AssistantEntity
        eventDao.insertAll(EventEntity(id = 1, description = "hola", name = "que", place = "e", date = "fdsf", hour = "sf"))

    }


    @After
    fun tearDown() {
        dataBase.close()
    }
}
