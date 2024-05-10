package com.longtq.domain.entity

data class Countries(
    var flags: Flags? = Flags(),
    var name: Name? = Name()
)

data class Flags(
    var png: String? = null,
    var svg: String? = null,
    var alt: String? = null
)

data class Name(
    var common: String? = null,
    var official: String? = null,
    var nativeName: NativeName? = NativeName()
)

data class NativeName(
    var ron: Ron? = Ron()
)

data class Ron(
    var official: String? = null,
    var common: String? = null

)