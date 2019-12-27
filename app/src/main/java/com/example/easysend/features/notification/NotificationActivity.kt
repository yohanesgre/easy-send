package com.example.easysend.features.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.R
import com.example.easysend.databinding.ActivityNotificationBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.notification.adapter.NotificationAdapter
import com.example.easysend.features.notification.data.model.ItemNotification

class NotificationActivity: AppCompatActivity(), Injectable{

    private lateinit var binding:ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf(
            ItemNotification(
                "Waktu anda kurang dari 2jam untuk memulai kegiatan",
                "12:00, 19/10/2010, No. Order 10982\n Jalan Surato Sari, Jakarta - Jalan Rungkut 23 Surabaya",
                false),
            ItemNotification(
                "Waktu anda kurang dari 2jam untuk memulai kegiatan",
                "12:00, 19/10/2010, No. Order 10982\n Jalan Surato Sari, Jakarta - Jalan Rungkut 23 Surabaya",
                true)
        )
        binding = DataBindingUtil.setContentView<ActivityNotificationBinding>(this, R.layout.activity_notification).apply {
            toolbar.title = "Notifications"
            rvNotifikasi.apply {
                adapter = NotificationAdapter(list)
                layoutManager = LinearLayoutManager(this@NotificationActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}