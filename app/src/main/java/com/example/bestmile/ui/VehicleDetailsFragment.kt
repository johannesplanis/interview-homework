package com.example.bestmile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bestmile.R
import com.example.bestmile.databinding.FragmentVehicleDetailsBinding

class VehicleDetailsFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel

    private var binding: FragmentVehicleDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactoryProvider.vehiclesViewModelFactory
        )[VehiclesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentVehicleDetailsBinding>(
            inflater,
            R.layout.fragment_vehicle_details,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewmodel = viewModel
        }
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.vehicleDetailsAbandoned()
        binding = null
    }
}