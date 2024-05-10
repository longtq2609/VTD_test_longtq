package com.longtq.domain.usecase

import com.longtq.domain.entity.Countries
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class GetListCountriesFormLocalUseCase @Inject constructor(
    private val countriesRepository: Repository
) {
    suspend operator fun invoke(): List<Countries> {
        return countriesRepository.getCountriesFromLocal()
    }
}