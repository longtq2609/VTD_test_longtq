package com.longtq.vtd_test_longtq.countries.detail_countries_screen

import com.longtq.domain.entity.Countries

data class DetailCountriesViewState(
    val isLoading: Boolean = false,
    val countries: Countries? = null
)