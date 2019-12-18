package com.example.easysend.features.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.FragmentDashboardBinding
import com.example.easysend.features.delivery.activities.view.DeliveryActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding:FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.fragment_dashboard
        )
        binding.apply {
            btnMulai.setOnClickListener {
                val intent = Intent(this@MainActivity, DeliveryActivity::class.java)
                startActivity(intent)
            }
            btnKomisi.setOnClickListener {
                val intent = Intent(this@MainActivity, KomisiActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
