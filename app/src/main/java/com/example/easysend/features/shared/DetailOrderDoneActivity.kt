package com.example.easysend.features.shared

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryDetailDoneBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.delivery.DeliveryActivity

class DetailOrderDoneActivity : AppCompatActivity(), Injectable{

    var orderId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderId = intent.getIntExtra("orderId", 0)
        val binding:ActivityDeliveryDetailDoneBinding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail_done)
        binding.btnDetilOrder.setOnClickListener {
            startActivity(Intent(this, DeliveryActivity::class.java))
        }
    }
}