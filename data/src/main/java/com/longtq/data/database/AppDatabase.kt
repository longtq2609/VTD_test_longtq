package com.longtq.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.longtq.data.database.DAO.CountriesDAO
import com.longtq.data.database.model.Converters
import com.longtq.data.database.model.CountriesLocal

@Database(entities = [CountriesLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countriesDAO(): CountriesDAO
}