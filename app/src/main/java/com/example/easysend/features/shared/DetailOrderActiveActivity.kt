package com.example.easysend.features.shared

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryDetailActiveBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.delivery.DeliveryActivity

class DetailOrderActiveActivity : AppCompatActivity(), Injectable{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityDeliveryDetailActiveBinding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail_active)
        binding.btnMulai.setOnClickListener {
            startActivity(Intent(this, DeliveryActivity::class.java))
        }
    }
}