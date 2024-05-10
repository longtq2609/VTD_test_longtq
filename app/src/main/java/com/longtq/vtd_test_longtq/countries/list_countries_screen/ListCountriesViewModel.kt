package com.longtq.vtd_test_longtq.countries.list_countries_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.domain.entity.Countries
import com.longtq.domain.usecase.GetListCountriesFormLocalUseCase
import com.longtq.domain.usecase.GetListCountriesUseCase
import com.longtq.domain.usecase.SaveListCountriesToLocalUseCase
import com.longtq.vtd_test_longtq.until.sendEvent
import com.longtq.vtd_test_longtq.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCountriesViewModel @Inject constructor(
    private val getListCountriesUseCase: GetListCountriesUseCase,
    private val saveListCountriesToLocalUseCase: SaveListCountriesToLocalUseCase,
    private val getCountriesToLocalUseCase: GetListCountriesFormLocalUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListCountriesViewState())
    val state = _state.asStateFlow()

    private var listCountries: List<Countries>? = null

    init {
        getCountriesFromLocal()

    }

    private fun getCountriesFromLocal() {
        viewModelScope.launch {
            val listCountriesLocal = getCountriesToLocalUseCase.invoke()
            if (listCountriesLocal.isNotEmpty()) {
                listCountries = listCountriesLocal

                _state.update {
                    it.copy(countries = listCountriesLocal)
                }
            } else {
                getCountries()
            }
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            getListCountriesUseCase.invoke()
                .map { item ->
                    listCountries = item
                    _state.update { it.copy(countries = item) }
                    saveListCountriesToLocalUseCase.invoke(item)
                }
                .mapLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun searchCountries(input: String) {
        Log.e("longtq", "searchCountries: $input")
        viewModelScope.launch {
            val currentList = listCountries
            val filteredList = currentList?.filter { countries ->
                countries.name?.common?.contains(input, ignoreCase = true) ?: false
            }
            _state.value = _state.value.copy(countries = filteredList ?: emptyList())
        }

    }
}