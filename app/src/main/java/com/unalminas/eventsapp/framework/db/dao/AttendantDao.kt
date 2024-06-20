package com.unalminas.eventsapp.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.unalminas.eventsapp.framework.db.entity.AttendanceEntity

@Dao
interface AttendantDao {

    @Query("SELECT * FROM Attendants")
    fun getAll(): List<AttendanceEntity>

    @Query("SELECT * FROM Attendants WHERE id = :id")
    fun getAttendantById(id: Int): AttendanceEntity

    @Update
    suspend fun update(attendant: AttendanceEntity)

    @Query("DELETE FROM Attendants WHERE id= :attendantId")
    suspend fun deleteAttendantById(attendantId: Int)

    @Query("SELECT * FROM Attendants WHERE eventId= :eventId")
    suspend fun getAttendantByEventId(eventId: Int): List<AttendanceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAttendant(vararg users: AttendanceEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAttendantOutId(vararg users: AttendanceEntity): List<Long>

}
