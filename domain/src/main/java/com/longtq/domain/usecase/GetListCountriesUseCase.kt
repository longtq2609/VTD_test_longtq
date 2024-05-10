package com.longtq.domain.usecase

import arrow.core.Either
import com.longtq.domain.entity.Countries
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class GetListCountriesUseCase @Inject constructor(
    private val countriesRepository: Repository
) {
    suspend operator fun invoke(): Either<NetworkError, List<Countries>> {
        return countriesRepository.getCountries()
    }
}