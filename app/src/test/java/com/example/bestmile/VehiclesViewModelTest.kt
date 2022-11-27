package com.example.bestmile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestmile.ui.VehiclesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class VehiclesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `init view model loads vehicles ids into state`() = runTest {
        val testDispatcher = StandardTestDispatcher()
        val viewModel = VehiclesViewModel(TestDataProvider.testApiAllNear, testDispatcher)

        launch {
            viewModel.loadData()
        }

        testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.state.value!!.items.size == 4)
    }

    @Test
    fun `init view model with all vehicles nearby exposes correct number of nearby vehicles`() =
        runTest {
            val testDispatcher = StandardTestDispatcher()
            val viewModel = VehiclesViewModel(TestDataProvider.testApiAllNear, testDispatcher)

            launch {
                viewModel.loadData()
            }

            testDispatcher.scheduler.advanceUntilIdle()

            assert(viewModel.state.value!!.nearbyVehicles == 4)
        }

    @Test
    fun `init view model with no vehicles nearby exposes correct number of nearby vehicles`() =
        runTest {
            val testDispatcher = StandardTestDispatcher()
            val viewModel = VehiclesViewModel(TestDataProvider.testApiNoneNear, testDispatcher)

            launch {
                viewModel.loadData()
            }

            testDispatcher.scheduler.advanceUntilIdle()

            assert(viewModel.state.value!!.nearbyVehicles == 0)
        }

    @Test
    fun `item clicked selects vehicle`() = runTest {
        val testDispatcher = StandardTestDispatcher()
        val viewModel = VehiclesViewModel(TestDataProvider.testApiAllNear, testDispatcher)

        launch {
            viewModel.loadData()
        }

        testDispatcher.scheduler.advanceUntilIdle()

        val firstItem = viewModel.state.value!!.listItems.first()

        viewModel.itemClicked(firstItem)

        viewModel.selectedVehicle.observeForever {}

        assert(viewModel.selectedItem.value!!.id == firstItem.id)
        assert(viewModel.selectedVehicle.getOrAwaitValue()!!.id == firstItem.id)
    }
}