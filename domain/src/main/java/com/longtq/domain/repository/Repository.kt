package com.longtq.domain.repository

import arrow.core.Either
import com.longtq.domain.entity.Countries
import com.longtq.domain.entity.NetworkError

interface Repository {
    suspend fun getCountries(): Either<NetworkError, List<Countries>>
    suspend fun saveCountriesToLocal(countries: List<Countries>)
    suspend fun getCountriesFromLocal(): List<Countries>
}