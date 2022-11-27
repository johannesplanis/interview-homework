package com.example.bestmile.ui

import androidx.lifecycle.*
import com.example.bestmile.data.VehicleApi
import com.example.bestmile.data.model.Vehicle
import com.example.bestmile.data.model.VehicleDetails
import com.example.bestmile.ui.model.VehicleDetailsViewModel
import com.example.bestmile.ui.model.VehiclesListItemViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class VehiclesViewModel(
    private val vehicleApi: VehicleApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    val state: LiveData<VehiclesListState>
        get() = mutableVehiclesListState
    val selectedItem: LiveData<Vehicle?>
        get() = mutableSelectedVehicle

    val selectedVehicle: LiveData<VehicleDetailsViewModel?>
        get() = mutableSelectedVehicle.combineWith(mutableVehiclesListState) { vehicle, vehiclesListState ->
            vehicle?.let {
                val item = vehiclesListState?.items?.get(vehicle.id)
                VehicleDetailsViewModel(
                    id = it.id,
                    near = item.isFarAwayLabel(),
                    location = item?.location.toString()
                )
            }
        }

    val itemClicked: (VehiclesListItemViewModel) -> Unit = { item ->
        mutableSelectedVehicle.postValue(Vehicle(item.id))
    }

    fun vehicleDetailsAbandoned() {
        mutableSelectedVehicle.value = null
    }

    private val mutableVehiclesListState = MutableLiveData(VehiclesListState(mapOf()))
    private val mutableSelectedVehicle: MutableLiveData<Vehicle?> = MutableLiveData()

    init {
        loadData()

        kotlin.concurrent.timer("refresh locations", false, 0L, 5000L) {
            viewModelScope.launch(coroutineDispatcher) {
                state.value?.listItems?.forEach { item ->
                    val details = vehicleApi.getVehicleDetails(item.id)
                    val items = state.value?.items.orEmpty()
                    mutableVehiclesListState.postValue(VehiclesListState(items.plus(item.id to details)))
                }
            }
        }
    }

    fun loadData() {
        viewModelScope.launch(coroutineDispatcher) {
            kotlin.runCatching {
                val vehicles = vehicleApi.getVehicles()

                mutableVehiclesListState.postValue(
                    VehiclesListState(vehicles.map { it.id to null }.toMap())
                )
            }.getOrElse {
                println("Error ${it.message}")
            }
        }
    }

    data class VehiclesListState(val items: Map<String, VehicleDetails?>) {
        val nearbyVehicles: Int
            get() = items.count { it.value?.location?.isNearBurgers() ?: false }

        val listItems: List<VehiclesListItemViewModel>
            get() = items.map {
                VehiclesListItemViewModel(
                    id = it.key,
                    near = it.value.isFarAwayLabel()
                )
            }
    }
}

fun VehicleDetails?.isFarAwayLabel() = when (this?.location?.isNearBurgers()) {
    true -> "close"
    else -> "far away"
}