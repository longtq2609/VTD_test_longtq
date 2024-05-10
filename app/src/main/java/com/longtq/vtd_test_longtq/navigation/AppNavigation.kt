package com.longtq.vtd_test_longtq.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.longtq.vtd_test_longtq.countries.detail_countries_screen.detailCountriesScreen
import com.longtq.vtd_test_longtq.countries.list_countries_screen.listCountriesScreen
import com.longtq.vtd_test_longtq.countries.list_countries_screen.listCountriseNavigation

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = listCountriseNavigation
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        listCountriesScreen { navController.detailCountriesScreen(it) }
        detailCountriesScreen { navController.popBackStack() }
    }
}