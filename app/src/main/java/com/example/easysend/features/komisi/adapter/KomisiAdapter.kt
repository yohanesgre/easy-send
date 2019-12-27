package com.example.easysend.features.komisi.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.databinding.ItemKomisiBinding
import com.example.easysend.extentions.formatDateTime
import com.example.easysend.features.komisi.data.model.KomisiItem

class KomisiAdapter(private var listItem:List<KomisiItem>) : RecyclerView.Adapter<KomisiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemKomisiBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }
    inner class ViewHolder (
        private val binding : ItemKomisiBinding
    ) : RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: KomisiItem) {
            binding.apply {
                tvAlamatTujuan.text = item.tujuan
                tvWaktuSampai.text = item.waktuSampai.formatDateTime("yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
                tvKomisi.text = item.jumlah
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    fun submitList(newList:List<KomisiItem>){
        listItem = newList
    }
}
