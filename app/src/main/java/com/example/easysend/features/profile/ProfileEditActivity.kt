package com.example.easysend.features.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityEditProfileBinding
import com.example.easysend.di.Injectable

class ProfileEditActivity : AppCompatActivity(), Injectable{

    private lateinit var binding:ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        binding.btnSimpan.setOnClickListener { finish() }
    }
}