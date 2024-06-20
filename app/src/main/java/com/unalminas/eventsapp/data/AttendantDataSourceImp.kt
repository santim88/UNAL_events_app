package com.unalminas.eventsapp.data

import com.unalminas.eventsapp.domain.Attendant
import com.unalminas.eventsapp.framework.db.dao.AttendantDao
import com.unalminas.eventsapp.framework.db.toAttendant
import com.unalminas.eventsapp.framework.db.toAttendantEntity
import javax.inject.Inject

class AttendantDataSourceImp @Inject constructor(
    private val AttendantDao: AttendantDao
) : AttendantDataSource {
    override suspend fun getAttendantsList(): List<Attendant> = AttendantDao.getAll().map {
        it.toAttendant()
    }

    override suspend fun getAttendantById(id: Int): Attendant {
        return AttendantDao.getAttendantById(id).toAttendant()
    }

    override suspend fun deleteAttendantById(attendantId: Int) {
        AttendantDao.deleteAttendantById(attendantId)
    }

    override suspend fun updateAttendant(attendant: Attendant) {
        AttendantDao.update(attendant.toAttendantEntity())
    }

    override suspend fun saveAttendant(attendant: Attendant) {
        AttendantDao.insertAttendant(attendant.toAttendantEntity())
    }

    override suspend fun getAttendantByEventId(eventId: Int): List<Attendant> =
        AttendantDao.getAttendantByEventId(eventId).map {
            it.toAttendant()
        }

    override suspend fun insertAttendantOutId(attendant: Attendant): List<Long> {
        return AttendantDao.insertAttendantOutId(attendant.toAttendantEntity())
    }
}
