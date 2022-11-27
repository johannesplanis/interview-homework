package com.example.bestmile.ui.model

data class VehiclesListItemViewModel(
    val id: String,
    val near: String
)

data class VehicleDetailsViewModel(
    val id: String,
    val near: String?,
    val location: String?
)