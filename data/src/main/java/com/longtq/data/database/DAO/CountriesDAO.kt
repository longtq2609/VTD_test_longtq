package com.longtq.data.database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.longtq.data.database.model.CountriesLocal

@Dao
interface CountriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountriesLocal>)

    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<CountriesLocal>
}