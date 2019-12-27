package com.example.easysend.features.point

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivityPointRewardProcessBinding
import com.example.easysend.di.Injectable

class PointProcessActivity : AppCompatActivity(), Injectable{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPointRewardProcessBinding>(this, R.layout.activity_point_reward_process).apply{
            btnOk.setOnClickListener{
                finish()
            }
        }
    }
}