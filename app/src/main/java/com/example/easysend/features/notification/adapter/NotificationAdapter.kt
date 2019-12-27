package com.example.easysend.features.notification.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.R
import com.example.easysend.databinding.ItemNotifikasiBinding
import com.example.easysend.features.notification.data.model.ItemNotification
import com.example.easysend.features.shared.DetailOrderActiveActivity

class NotificationAdapter(private var itemList:List<ItemNotification>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNotifikasiBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun submitList(newItemList:List<ItemNotification>){
        itemList = newItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding:ItemNotifikasiBinding
    ):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(item:ItemNotification){
            binding.apply {
                titleNotif.text = item.title
                contentNotif.text = item.content
                if (item.isRead){
                    layoutNotif.setBackgroundColor(R.color.colorPrimary)
                }
                layoutNotif.setOnClickListener {
                    root.context.startActivity(Intent(root.context, DetailOrderActiveActivity::class.java))
                }
            }
        }
    }
}