package com.unalminas.eventsapp.repository

import com.unalminas.eventsapp.data.AttendantDataSource
import com.unalminas.eventsapp.domain.Attendant

class AttendantRepositoryImpl(
    private val attendantDataSource: AttendantDataSource,
) : AttendantRepository {

    override suspend fun getAttendantList(): List<Attendant> {
        return attendantDataSource.getAttendantsList()
    }

    override suspend fun getAttendantListByEvent(eventId: Int): List<Attendant> {
        return attendantDataSource.getAttendantByEventId(eventId)
    }

    override suspend fun deleteAttendantById(id: Int) {
        return attendantDataSource.deleteAttendantById(id)
    }

    override suspend fun getAttendantById(id: Int): Attendant {
        return attendantDataSource.getAttendantById(id)
    }

    override suspend fun updateAttendant(attendant: Attendant) {
        return attendantDataSource.updateAttendant(attendant)
    }

    override suspend fun insertAttendant(attendant: Attendant) {
        return attendantDataSource.saveAttendant(attendant)
    }

    override suspend fun insertAttendantOutId(attendant: Attendant): List<Long> {
        return attendantDataSource.insertAttendantOutId(attendant)
    }
}
