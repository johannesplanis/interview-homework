package com.example.bestmile.data.model

import com.google.gson.annotations.SerializedName

data class VehicleDetails(
    @SerializedName("vehicleId")
    val id: String,
    @SerializedName("location")
    val location: Location
)