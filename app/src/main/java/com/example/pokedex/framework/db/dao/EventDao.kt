package com.example.pokedex.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.framework.db.entity.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAll(): List<EventEntity>

    @Query("SELECT * FROM events WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: EventEntity)

    @Delete
    fun delete(user: EventEntity)
}
