package com.example.bestmile.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmile.ui.model.VehiclesListItemViewModel

@BindingAdapter("itemViewModels")
fun bindItemViewModels(
    recyclerView: RecyclerView,
    itemViewModels: List<VehiclesListItemViewModel>?
) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.replaceItems(itemViewModels)
}

@BindingAdapter("itemClicked")
fun bindClick(
    recyclerView: RecyclerView,
    onClick: (VehiclesListItemViewModel) -> Unit
) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.itemClicked = onClick
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): VehiclesAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is VehiclesAdapter) {
        recyclerView.adapter as VehiclesAdapter
    } else {
        val bindableRecyclerAdapter = VehiclesAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}