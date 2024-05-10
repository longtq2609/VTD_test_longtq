package com.longtq.data

import arrow.core.Either
import com.longtq.data.api.ApiService
import com.longtq.data.api.mapper.toDomainModel
import com.longtq.data.api.mapper.toGeneralError
import com.longtq.data.api.mapper.toLocal
import com.longtq.data.api.model.CountriesResponse
import com.longtq.data.database.AppDatabase
import com.longtq.data.database.mapper.toDomainModel
import com.longtq.domain.entity.Countries
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : Repository {
    override suspend fun getCountries(): Either<NetworkError, List<Countries>> {
        return Either.catch {
            val countries: List<CountriesResponse> = apiService.getCountries()
            countries.map { it.toDomainModel() }
        }.mapLeft { it.toGeneralError() }
    }

    override suspend fun saveCountriesToLocal(countries: List<Countries>) {
        val countriesResponse: List<CountriesResponse> = apiService.getCountries()
        appDatabase.countriesDAO().insertCountries(countriesResponse.map { it.toLocal() })
    }

    override suspend fun getCountriesFromLocal(): List<Countries> {
        val countriesLocal = appDatabase.countriesDAO().getCountries()
        return countriesLocal.map { it.toDomainModel() }
    }
}