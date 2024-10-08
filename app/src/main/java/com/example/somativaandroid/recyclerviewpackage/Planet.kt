package com.example.somativaandroid.recyclerviewpackage

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("pl_name") val name: String,
    @SerializedName("gaia_id") val gaiaId: String,
    @SerializedName("disc_year") val discoveryYear: Int,
    @SerializedName("discoverymethod") val discoveryMethod: String,
    @SerializedName("disc_locale") val discoveryLocale: String,
    @SerializedName("disc_facility") val discoveryFacility: String,
    @SerializedName("disc_telescope") val discoveryTelescope: String
)
