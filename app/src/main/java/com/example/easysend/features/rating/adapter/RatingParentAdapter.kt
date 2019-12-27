package com.example.easysend.features.rating.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.databinding.ItemRatingRvBinding
import com.example.easysend.features.rating.data.model.ItemRatingParent

class RatingParentAdapter (private val parents : List<ItemRatingParent>) : RecyclerView.Adapter<RatingParentAdapter.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(ItemRatingRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(parents[position])
    }

    inner class ViewHolder(private val binding:ItemRatingRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:ItemRatingParent){
            binding.apply {
                val childLayoutManager = LinearLayoutManager(rvRating.context, LinearLayoutManager.HORIZONTAL, false)
                childLayoutManager.initialPrefetchItemCount = 0
                rvRating.apply {
                    layoutManager = childLayoutManager
                    adapter = RatingChildAdapter(item.rating)
                    setRecycledViewPool(viewPool)
                }
            }
        }
    }
}