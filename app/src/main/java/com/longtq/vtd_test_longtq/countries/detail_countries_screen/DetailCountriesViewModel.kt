package com.longtq.vtd_test_longtq.countries.detail_countries_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.longtq.domain.entity.Countries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject


@HiltViewModel
class DetailCountriesViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(DetailCountriesViewState())
    val state = _state.asStateFlow()

    val countries = stateHandle["countries"] ?: ""
    private val decodedUri = URLDecoder.decode(countries, StandardCharsets.UTF_8.toString())
    private val gson = Gson()
    private val countriesDetail = gson.fromJson(decodedUri, Countries::class.java)

    init {
        getDetailCountries()
    }

    private fun getDetailCountries() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(countries = countriesDetail)
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}