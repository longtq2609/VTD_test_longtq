package com.longtq.vtd_test_longtq.countries.detail_countries_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.longtq.domain.entity.Countries
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val detailCountriesNavigation = "detailCountriesNavigation"

@RequiresApi(Build.VERSION_CODES.O)
fun NavController.detailCountriesScreen(countries: Countries) {
    val gson = Gson()
    val json = gson.toJson(countries)
    val encodedUri = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
    navigate("$detailCountriesNavigation/${encodedUri}")
}

fun NavGraphBuilder.detailCountriesScreen(
    onBackClick: () -> Unit
) {
    composable(route = "$detailCountriesNavigation/{countries}") {
        DetailCountriesScreen(onBackClick)
    }

}
