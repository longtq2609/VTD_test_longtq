package com.longtq.vtd_test_longtq.countries.detail_countries_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.longtq.vtd_test_longtq.R
import com.longtq.vtd_test_longtq.until.components.LoadingDialog
import com.longtq.vtd_test_longtq.until.components.MyTopAppBar


@Composable
internal fun DetailCountriesScreen(onBackClick: () -> Unit) {
    val viewModel: DetailCountriesViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    DetailCountries(state = state, onBackClick = onBackClick)
}

@Composable
fun DetailCountries(
    state: State<DetailCountriesViewState>,
    onBackClick: () -> Unit
) {
    LoadingDialog(isLoading = state.value.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                text = stringResource(id = R.string.title_detail_countries),
                onBackClick = onBackClick,
                isShowIconBack = true
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxWidth()
        ) {
            Text(text = "FLAGS", style = MaterialTheme.typography.titleLarge)

            Row {
                Text(text = "PNG")
                AsyncImage(
                    model = state.value.countries?.flags?.png,
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                )
            }
            Row {
                Text(text = "ALT: ")
                Text(text = state.value.countries?.flags?.alt ?: "")
            }
            Text(text = "NAME", style = MaterialTheme.typography.titleLarge)


            Row {
                Text(text = "COMMON: ")
                Text(text = state.value.countries?.name?.common ?: "")
            }
            Row {
                Text(text = "OFFICIAL: ")
                Text(text = state.value.countries?.name?.official ?: "")
            }

        }
    }
}