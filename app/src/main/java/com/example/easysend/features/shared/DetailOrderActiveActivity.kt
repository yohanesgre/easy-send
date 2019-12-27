package com.example.easysend.features.shared

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.afollestad.materialdialogs.MaterialDialog
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryDetailActiveBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.delivery.DeliveryActivity

class DetailOrderActiveActivity : AppCompatActivity(), Injectable{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityDeliveryDetailActiveBinding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail_active)
        binding.toolbar.title="Detail Order"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.btnMulai.setOnClickListener {
            startActivity(Intent(this, DeliveryActivity::class.java))
        }

        binding.btnMulai.setOnClickListener {
            MaterialDialog(this).show {
                icon(R.drawable.ic_warning)
                message(text="Apa Anda yakin ingin memulai kegiatan?")
                positiveButton(text = "YA"){
                    startActivity(Intent(this@DetailOrderActiveActivity, DeliveryActivity::class.java))
                }
                negativeButton(text="TIDAK") {
                    dismiss()
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}