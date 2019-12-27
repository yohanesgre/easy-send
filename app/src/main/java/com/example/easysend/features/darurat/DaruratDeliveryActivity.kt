package com.example.easysend.features.darurat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityFasilitasDaruratBinding
import com.example.easysend.di.Injectable

class DaruratDeliveryActivity : AppCompatActivity(), Injectable {

    private lateinit var binding:ActivityFasilitasDaruratBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fasilitas_darurat)
        binding.btnOk.setOnClickListener {
            finish()
        }
    }

}