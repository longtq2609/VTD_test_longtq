package com.longtq.vtd_test_longtq.countries.list_countries_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.domain.entity.Countries
import com.longtq.vtd_test_longtq.R
import com.longtq.vtd_test_longtq.countries.list_countries_screen.components.CountriesCard
import com.longtq.vtd_test_longtq.until.components.LoadingDialog
import com.longtq.vtd_test_longtq.until.components.MyTopAppBar


@Composable
internal fun ListCountriesScreen(onClickCountries: (Countries) -> Unit) {
    val viewModel: ListCountriesViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    ListCountries(state = state, onClickCountries = {
        onClickCountries(it)
    }) {
        viewModel.searchCountries(it)
    }
}


@Composable
fun ListCountries(
    state: State<ListCountriesViewState>,
    onClickCountries: (Countries) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    val searchQuery: MutableState<String> = remember { mutableStateOf("") }

    LoadingDialog(isLoading = state.value.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(text = stringResource(id = R.string.title_list_countries)) }
    ) {
        Column(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                value = searchQuery.value,  // Ensure value is of type String
                onValueChange = { input ->
                    searchQuery.value = input
                    onSearchQueryChanged(input)
                }
            )

            LazyColumn {
                items(state.value.countries) { countries ->
                    CountriesCard(countries = countries, onClickItem = {
                        onClickCountries(countries)
                    })
                }
            }
        }

    }
}
