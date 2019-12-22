package com.example.easysend.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.databinding.ItemRatingBinding

class RatingAdapter : ListAdapter<Int, RatingAdapter.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(val binding:ItemRatingBinding) : RecyclerView.ViewHolder(binding.root)
}


private class ItemDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}