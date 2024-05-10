package com.longtq.data.api.model

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("flags")
    var flags: FlagsResponse? = FlagsResponse(),
    @SerializedName("name")
    var name: NameResponse? = NameResponse()
)


data class FlagsResponse(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)

data class NameResponse(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeNameResponse? = NativeNameResponse()
)

data class NativeNameResponse(
    @SerializedName("ron") var ron: RonResponse? = RonResponse()
)


data class RonResponse(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null

)