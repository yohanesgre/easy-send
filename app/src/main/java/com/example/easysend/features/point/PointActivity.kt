package com.example.easysend.features.point

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.R
import com.example.easysend.databinding.ActivityPoinBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.point.adapter.PointRewardAdapter
import com.example.easysend.features.point.data.model.ItemPointReward

class PointActivity : AppCompatActivity(), Injectable{
    private lateinit var binding:ActivityPoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf(
            ItemPointReward(R.drawable.ic_paket_umroh, "Paket Umroh\n15500 Poin"),
            ItemPointReward(R.drawable.ic_sepeda, "Sepeda\n4500 Poin"),
            ItemPointReward(R.drawable.ic_tv_led, "TV LED\n1000 Poin")
        )
        binding = DataBindingUtil.setContentView<ActivityPoinBinding>(this, R.layout.activity_poin).apply{
            rvReward.apply {
                adapter = PointRewardAdapter(list)
                layoutManager = LinearLayoutManager(this@PointActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
        binding.toolbar.title="Poin"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}