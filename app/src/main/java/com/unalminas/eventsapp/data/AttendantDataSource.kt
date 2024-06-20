package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Attendant

interface AttendantDataSource {
    suspend fun getAttendantsList(): List<Attendant>
    suspend fun deleteAttendantById(assistantId: Int)
    suspend fun updateAttendant(attendant: Attendant)
    suspend fun getAttendantById(id: Int): Attendant
    suspend fun saveAttendant(attendant: Attendant)
    suspend fun getAttendantByEventId(id: Int): List<Attendant>
    suspend fun insertAttendantOutId(attendant: Attendant): List<Long>
}
