package com.example.bestmile

import com.example.bestmile.data.VehicleApi
import com.example.bestmile.data.model.Location
import com.example.bestmile.data.model.Vehicle
import com.example.bestmile.data.model.VehicleDetails

object TestDataProvider {
    val testApiAllNear = object : VehicleApi {
        override suspend fun getVehicleDetails(id: String): VehicleDetails {
            return VehicleDetails(id, Location.burgersLocation)
        }

        override suspend fun getVehicles(): List<Vehicle> {
            return listOf(
                Vehicle("123"),
                Vehicle("456"),
                Vehicle("789"),
                Vehicle("012")
            )
        }
    }

    val testApiNoneNear = object : VehicleApi {
        override suspend fun getVehicleDetails(id: String): VehicleDetails {
            return VehicleDetails(id, Location(123.0, 456.0))
        }

        override suspend fun getVehicles(): List<Vehicle> {
            return listOf(
                Vehicle("123"),
                Vehicle("456"),
                Vehicle("789"),
                Vehicle("012")
            )
        }
    }
}