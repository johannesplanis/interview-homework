package com.example.bestmile.data.model

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("vehicleId")
    val id: String,
)