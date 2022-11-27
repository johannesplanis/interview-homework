package com.example.bestmile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.example.bestmile.R
import com.example.bestmile.databinding.FragmentVehiclesListBinding

class VehiclesListFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel

    private var binding: FragmentVehiclesListBinding? = null

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
        val fragmentBinding = DataBindingUtil.inflate<FragmentVehiclesListBinding?>(
            inflater,
            R.layout.fragment_vehicles_list,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewmodel = viewModel
        }

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.selectedItem.observe(requireParentFragment()) {
            if (it != null) {
                findNavController().safeNavigate(ActionOnlyNavDirections(R.id.vehiclesListFragment_to_vehicleDetailsFragment))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}