package com.example.easysend.features.rating.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.databinding.ItemRatingBinding

class RatingChildAdapter(rating : Int)
    : RecyclerView.Adapter<RatingChildAdapter.ViewHolder>(){

    private var list:List<Int>

    init {
        val arrayList = ArrayList<Int>()
        (1..rating).forEach{ item ->
            arrayList.add(item)
        }
        list = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }


    inner class ViewHolder(binding:ItemRatingBinding) : RecyclerView.ViewHolder(binding.root){
    }
}