package com.longtq.vtd_test_longtq.countries.list_countries_screen

import com.longtq.domain.entity.Countries

data class ListCountriesViewState(
    val isLoading: Boolean = false,
    val countries: List<Countries> = emptyList(),
    val error: String? = null
)