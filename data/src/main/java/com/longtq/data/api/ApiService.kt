package com.longtq.data.api

import com.longtq.data.api.model.CountriesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("all?fields=name,flags")
    suspend fun getCountries(): List<CountriesResponse>
}