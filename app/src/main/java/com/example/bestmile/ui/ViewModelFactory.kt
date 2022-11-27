package com.example.bestmile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bestmile.data.VehicleApi
import com.example.bestmile.data.VehicleApiProvider
import kotlinx.coroutines.Dispatchers

object ViewModelFactoryProvider {
    val vehiclesViewModelFactory by lazy {
        VehiclesViewModelFactory(VehicleApiProvider.api)
    }
}

class VehiclesViewModelFactory(private val vehicleApi: VehicleApi) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehiclesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehiclesViewModel(vehicleApi, Dispatchers.IO) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}