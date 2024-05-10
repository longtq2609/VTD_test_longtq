package com.longtq.data.api.mapper

import com.longtq.data.api.model.CountriesResponse
import com.longtq.data.api.model.FlagsResponse
import com.longtq.data.api.model.NameResponse
import com.longtq.data.api.model.NativeNameResponse
import com.longtq.data.api.model.RonResponse
import com.longtq.data.database.model.CountriesLocal
import com.longtq.data.database.model.FlagsLocal
import com.longtq.data.database.model.NameLocal
import com.longtq.data.database.model.NativeNameLocal
import com.longtq.data.database.model.RonLocal
import com.longtq.domain.entity.ApiError
import com.longtq.domain.entity.Countries
import com.longtq.domain.entity.Flags
import com.longtq.domain.entity.Name
import com.longtq.domain.entity.NativeName
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.Ron
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toGeneralError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this
    )
}

fun CountriesResponse.toDomainModel(): Countries {
    return Countries(
        name = name?.toDomainModel(),
        flags = flags?.toDomainModel()
    )
}

fun FlagsResponse.toDomainModel(): Flags {
    return Flags(
        png = png,
        svg = svg,
        alt = alt
    )
}

fun NameResponse.toDomainModel(): Name {
    return Name(
        common = common,
        official = official,
        nativeName = nativeName?.toDomainModel()
    )
}

fun NativeNameResponse.toDomainModel(): NativeName {
    return NativeName(
        ron = ron?.toDomainModel()
    )
}

fun RonResponse.toDomainModel(): Ron {
    return Ron(
        official = official,
        common = common
    )
}

fun CountriesResponse.toLocal(): CountriesLocal {
    return CountriesLocal(
        name = name?.toLocal(),
        flags = flags?.toLocal()
    )
}

fun FlagsResponse.toLocal(): FlagsLocal {
    return FlagsLocal(
        png = png,
        svg = svg,
        alt = alt
    )
}

fun NameResponse.toLocal(): NameLocal {
    return NameLocal(
        common = common,
        official = official,
        nativeName = nativeName?.toLocal()
    )
}

fun NativeNameResponse.toLocal(): NativeNameLocal {
    return NativeNameLocal(
        ron = ron?.toLocal()
    )
}

fun RonResponse.toLocal(): RonLocal {
    return RonLocal(
        official = official,
        common = common
    )
}