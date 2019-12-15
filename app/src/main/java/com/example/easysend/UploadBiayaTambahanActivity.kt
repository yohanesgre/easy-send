package com.example.easysend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.databinding.ActivityUploadBiayaTambahanBinding

class UploadBiayaTambahanActivity:AppCompatActivity() {

    private lateinit var binding:ActivityUploadBiayaTambahanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload_biaya_tambahan)
        binding.appBarLayout.toolbar.title = "Upload Biaya Tambahan"
        setSupportActionBar(binding.appBarLayout.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}