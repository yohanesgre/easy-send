package com.example.easysend.features.point.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.databinding.ItemPoinRewardBinding
import com.example.easysend.features.point.PointProcessActivity
import com.example.easysend.features.point.data.model.ItemPointReward

class PointRewardAdapter(
    private val list:List<ItemPointReward>
) : RecyclerView.Adapter<PointRewardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPoinRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding:ItemPoinRewardBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ItemPointReward){
            binding.apply {
                ivImage.setImageResource(item.image)
                content.text = (item.content)
                layoutReward.setOnClickListener {
                    root.context.startActivity(Intent(root.context, PointProcessActivity::class.java))
                }
            }
        }
    }

}