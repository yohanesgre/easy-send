package com.example.easysend.features.rating

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.R
import com.example.easysend.databinding.ActivityRatingBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.rating.adapter.RatingParentAdapter
import com.example.easysend.features.rating.data.model.ItemRatingParent

class RatingActivity : AppCompatActivity(), Injectable{

    private lateinit var binding:ActivityRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf(
            ItemRatingParent("123456", "10/11/2019", 5),
            ItemRatingParent("312546", "10/11/2019", 3)
        )
        binding = DataBindingUtil.setContentView<ActivityRatingBinding>(this, R.layout.activity_rating).apply{
            rvRating.apply {
                adapter = RatingParentAdapter(list)
                layoutManager = LinearLayoutManager(this@RatingActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
        binding.toolbar.title="Rating"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}