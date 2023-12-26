package com.unalminas.eventsapp.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.unalminas.eventsapp.framework.db.entity.AssistantEntity

@Dao
interface AssistantDao {

    @Query("SELECT * FROM assistants")
    fun getAll(): List<AssistantEntity>

    @Query("SELECT * FROM assistants WHERE id = :id")
    fun getAssistantById(id: Int): AssistantEntity

    @Update
    suspend fun update(assistant: AssistantEntity)

    @Query("DELETE FROM assistants WHERE id= :assistantId")
    suspend fun deleteAssistantById(assistantId: Int)

    @Query("SELECT * FROM assistants WHERE eventId= :eventId")
    suspend fun getAssistantByEventId(eventId: Int): List<AssistantEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAssistant(vararg users: AssistantEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAssistantOutId(vararg users: AssistantEntity): List<Long>

}