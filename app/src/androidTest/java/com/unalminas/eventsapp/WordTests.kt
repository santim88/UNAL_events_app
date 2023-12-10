package com.unalminas.eventsapp

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.framework.db.dao.AssistantDao
import com.unalminas.eventsapp.framework.db.dao.EventDao
import com.unalminas.eventsapp.framework.db.database.AssistantDataBase
import com.unalminas.eventsapp.framework.db.toAssistantEntity
import com.unalminas.eventsapp.framework.db.toEventEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordTests {

    private lateinit var dataBase: AssistantDataBase
    private lateinit var assistantDao: AssistantDao
    private lateinit var eventDao: EventDao

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AssistantDataBase::class.java
        ).build()

        /*   dataBaseEvent = Room.inMemoryDatabaseBuilder(
               ApplicationProvider.getApplicationContext(),
               EventDatabase::class.java
           ).allowMainThreadQueries().build()*/

        assistantDao = dataBase.assistantDao()
        eventDao = dataBase.eventDao()

    }

    @Test
    fun getAssistantsFromEvent() = runBlocking {

        val event = Event(
            id = 100,
            name = "pruebas"
        )
        val assistant = Assistant(
            id = 1,
            name = "hola",
            identification = "quiero_comer",
            eventId = event.id
        )

        eventDao.insertAll(
            event.toEventEntity()
        )
        assistantDao.insertAssistant(
            assistant.toAssistantEntity()
        )

        val eventUnit = eventDao.getEventById(event.id)

        assertEquals(event.id, eventUnit.id)

        assistantDao.insertAssistant(
            Assistant(
                name = "hola2",
                identification = "quiero_comer2",
                eventId = event.id
            ).toAssistantEntity()
        )

        assistantDao.insertAssistant(
            Assistant(
                name = "hola2",
                identification = "quiero_comer3",
                eventId = event.id
            ).toAssistantEntity()
        )

        val assistants = assistantDao.getAssistantByEventId(eventId = event.id)

        assertNotNull(assistants)
        for (assistant in assistants) {
            Log.d("WordTests", "Assistant: $assistant")
        }
        // Assert that the list size is as expected
        assertEquals(3, assistants.size)

        // Assert that the first element in the list has the expected values
        assertEquals(assistant.name, assistants[0].name)
        assertEquals(assistant.identification, assistants[0].identification)
        assertEquals(assistant.eventId, assistants[0].eventId)
    }


    @After
    fun tearDown() {
        dataBase.close()
    }
}
