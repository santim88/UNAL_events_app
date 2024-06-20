package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.domain.Attendant

interface AttendantRepository {
    suspend fun getAttendantList(): List<Attendant>
    suspend fun insertAttendant(attendant: Attendant)
    suspend fun updateAttendant(attendant: Attendant)
    suspend fun getAttendantById(id: Int): Attendant
    suspend fun deleteAttendantById(id: Int)
    suspend fun getAttendantListByEvent(eventId: Int): List<Attendant>
    suspend fun insertAttendantOutId(attendant: Attendant): List<Long>
}
