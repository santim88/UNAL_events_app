package com.unalminas.eventsapp.domain.framework.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.unalminas.eventsapp.domain.framework.db.entity.AssistantEntity

@Dao
interface AssistantDao {

    @Query("SELECT * FROM assistants")
    fun getAll(): List<AssistantEntity>
}