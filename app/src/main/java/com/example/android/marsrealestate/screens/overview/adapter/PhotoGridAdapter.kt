package com.example.android.marsrealestate.screens.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.screens.overview.adapter.PhotoGridAdapter.MarsPropertyViewHolder

class PhotoGridAdapter : ListAdapter<MarsProperty,
        MarsPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty):
                Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty):
                Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MarsPropertyViewHolder {
        val layoutInflater = GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return MarsPropertyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    class MarsPropertyViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty

            //causes the update to execute immediately.
            binding.executePendingBindings()
        }
    }
}