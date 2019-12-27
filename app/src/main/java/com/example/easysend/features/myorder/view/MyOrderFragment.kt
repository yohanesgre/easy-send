package com.example.easysend.features.myorder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.easysend.databinding.FragmentMyOrderBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.myorder.adapter.ACTIVE_FRAGMENT_INDEX
import com.example.easysend.features.myorder.adapter.ARCHIVED_FRAGMENT_INDEX
import com.example.easysend.features.myorder.adapter.MyOrderPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MyOrderFragment : Fragment(), Injectable {

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt("UserID") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyOrderBinding.inflate(inflater, container, false)
        context?: return binding.root
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        binding.toolbar.title = "Bookings"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        viewPager.adapter =
            MyOrderPagerFragmentAdapter(
                this
            )

        TabLayoutMediator(tabLayout, viewPager) {tabs, position->
            tabs.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ACTIVE_FRAGMENT_INDEX -> "Booked"
            ARCHIVED_FRAGMENT_INDEX -> "Finished"
            else -> null
        }
    }
}
