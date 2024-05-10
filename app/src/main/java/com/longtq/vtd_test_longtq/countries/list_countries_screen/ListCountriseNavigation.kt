package com.longtq.vtd_test_longtq.countries.list_countries_screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.longtq.domain.entity.Countries

const val listCountriseNavigation = "listCountriseNavigation"

fun NavGraphBuilder.listCountriesScreen(
    onClickCountries: (Countries) -> Unit,
) {
    composable(route = listCountriseNavigation) {
        ListCountriesScreen(onClickCountries = onClickCountries)
    }
}