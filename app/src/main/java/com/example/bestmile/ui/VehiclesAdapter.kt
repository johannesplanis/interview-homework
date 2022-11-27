package com.example.bestmile.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmile.BR
import com.example.bestmile.R
import com.example.bestmile.ui.model.VehiclesListItemViewModel

fun interface ItemClickCallback {
    fun onClick()
}

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        itemViewModel: VehiclesListItemViewModel,
        callback: ItemClickCallback
    ) {
        binding.setVariable(BR.itemViewModel, itemViewModel)
        binding.setVariable(BR.callback, callback)
    }
}

class VehiclesAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val itemViewModels: MutableList<VehiclesListItemViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_vehicles_list,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemViewModels[position]
        holder.bind(item) { itemClicked(item) }
    }

    fun replaceItems(items: List<VehiclesListItemViewModel>?) {
        itemViewModels.clear()
        items?.let {
            itemViewModels.addAll(items)
        }

        notifyDataSetChanged()
    }

    var itemClicked: (VehiclesListItemViewModel) -> Unit = {}
}