package com.example.bestmile.data.model

import com.google.gson.annotations.SerializedName
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Location(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
) {
    private fun distanceKm(location: Location): Double {
        val earthRadius = 3958.75
        val dLat = Math.toRadians(location.latitude - latitude)
        val dLng = Math.toRadians(location.longitude - longitude)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(latitude)) * cos(Math.toRadians(location.latitude)) *
                sin(dLng / 2) * sin(dLng / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }

    fun isNearBurgers() = distanceKm(burgersLocation) < 1

    companion object {
        val burgersLocation = Location(46.5223916, 6.6314437)
    }
}