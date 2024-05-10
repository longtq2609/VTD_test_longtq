package com.longtq.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson


@Entity(tableName = "countries")
data class CountriesLocal(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "flags") var flags: FlagsLocal? = FlagsLocal(),
    @ColumnInfo(name = "name") var name: NameLocal? = NameLocal()
)

data class FlagsLocal(
    var png: String? = null,
    var svg: String? = null,
    var alt: String? = null
)

data class NameLocal(
    var common: String? = null,
    var official: String? = null,
    var nativeName: NativeNameLocal? = NativeNameLocal()
)

data class NativeNameLocal(
    var ron: RonLocal? = RonLocal()
)


data class RonLocal(
    var official: String? = null,
    var common: String? = null

)

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromFlagsLocal(flagsLocal: FlagsLocal?): String? {
        return gson.toJson(flagsLocal)
    }

    @TypeConverter
    fun toFlagsLocal(flagsLocalString: String?): FlagsLocal? {
        return gson.fromJson(flagsLocalString, FlagsLocal::class.java)
    }

    @TypeConverter
    fun fromNameLocal(nameLocal: NameLocal?): String? {
        return gson.toJson(nameLocal)
    }

    @TypeConverter
    fun toNameLocal(nameLocalString: String?): NameLocal? {
        return gson.fromJson(nameLocalString, NameLocal::class.java)
    }
}