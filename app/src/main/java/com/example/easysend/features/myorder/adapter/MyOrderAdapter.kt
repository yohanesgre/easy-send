package com.example.easysend.features.myorder.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.easysend.R
import com.example.easysend.databinding.ItemMyOrderBinding
import com.example.easysend.extentions.formatDateTime
import com.example.easysend.features.delivery.DeliveryActivity
import com.example.easysend.features.myorder.data.model.Order
import com.example.easysend.features.shared.DetailOrderActiveActivity
import com.example.easysend.features.shared.DetailOrderDoneActivity

class MyOrderAdapter(private var listItem:List<Order>) : RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMyOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }
    inner class ViewHolder (
        private val binding : ItemMyOrderBinding
    ) : RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Order) {
            binding.apply {
                if (item.finished){
                    btnMulai.visibility = View.GONE
                    btnLihatDetil.setOnClickListener {
                        root.context.startActivity(Intent(root.context, DetailOrderDoneActivity::class.java))
                    }
                }else{
                    btnLihatDetil.setOnClickListener {
                        root.context.startActivity(Intent(root.context, DetailOrderActiveActivity::class.java))
                    }
                }
                btnMulai.setOnClickListener {
                    binding.btnMulai.setOnClickListener {
                        MaterialDialog(binding.root.context).show {
                            icon(R.drawable.ic_warning)
                            message(text="Apa Anda yakin ingin memulai kegiatan?")
                            positiveButton(text = "YA"){
                                root.context.startActivity(Intent(binding.root.context, DeliveryActivity::class.java))
                            }
                            negativeButton(text="TIDAK") {
                                dismiss()
                            }
                        }
                    }
                }
                outputDate.text = item.date.formatDateTime("yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
                outputOrigin.text = item.origin
                outputDestination.text = item.destination
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    fun submitList(newList:List<Order>){
        listItem = newList
    }
}
