package com.example.easysend.features.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityLoginBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.MainActivity

class LoginActivity : AppCompatActivity(), Injectable{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnMasuk.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}