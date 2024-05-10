package com.longtq.data.database.mapper


import com.longtq.data.database.model.CountriesLocal
import com.longtq.data.database.model.FlagsLocal
import com.longtq.data.database.model.NameLocal
import com.longtq.data.database.model.NativeNameLocal
import com.longtq.data.database.model.RonLocal
import com.longtq.domain.entity.Countries
import com.longtq.domain.entity.Flags
import com.longtq.domain.entity.Name
import com.longtq.domain.entity.NativeName
import com.longtq.domain.entity.Ron

fun CountriesLocal.toDomainModel(): Countries {
    return Countries(
        name = name?.toDomainModel(),
        flags = flags?.toDomainModel()
    )
}

fun FlagsLocal.toDomainModel(): Flags {
    return Flags(
        png = png,
        svg = svg,
        alt = alt
    )
}

fun NameLocal.toDomainModel(): Name {
    return Name(
        common = common,
        official = official,
        nativeName = nativeName?.toDomainModel()
    )
}

fun NativeNameLocal.toDomainModel(): NativeName {
    return NativeName(
        ron = ron?.toDomainModel()
    )
}

fun RonLocal.toDomainModel(): Ron {
    return Ron(
        official = official,
        common = common
    )
}