package io.covid19.data.repositories.release

import io.covid19.data.models.ReleaseConfig

interface ReleaseRepository {

    suspend fun getCurrentReleaseConfig(): ReleaseConfig
}