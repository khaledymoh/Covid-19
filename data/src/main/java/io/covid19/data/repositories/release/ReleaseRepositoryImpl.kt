package io.covid19.data.repositories.release

import io.covid19.data.daos.RemoteReleaseDao
import io.covid19.data.models.ReleaseConfig

class ReleaseRepositoryImpl(private val remoteReleaseDao: RemoteReleaseDao) : ReleaseRepository {

    override suspend fun getCurrentReleaseConfig(): ReleaseConfig {
        return remoteReleaseDao.getCurrentReleaseConfig()
    }
}