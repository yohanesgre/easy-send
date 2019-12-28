package com.example.easysend.features.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityPilihAkunBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.login.LoginActivity

class PilihAkunActivity : AppCompatActivity(), Injectable{
    private lateinit var binding:ActivityPilihAkunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPilihAkunBinding>(this, R.layout.activity_pilih_akun).apply {
            layoutDriver.setOnClickListener {
                startActivity(Intent(this@PilihAkunActivity, LoginActivity::class.java))
            }
            layoutKaryawan.setOnClickListener {
                startActivity(Intent(this@PilihAkunActivity, LoginActivity::class.java))
            }
        }
    }
}