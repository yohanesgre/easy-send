package com.example.easysend.features.delivery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.databinding.FragmentDeliverySelesaiBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.delivery.adapter.RatingDeliveryAdapter

class DeliverySelesaiFragment: Fragment(), Injectable {

    private lateinit var binding:FragmentDeliverySelesaiBinding
    private lateinit var adapterRating:RatingDeliveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliverySelesaiBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    fun initUI(){
        requireActivity().onBackPressedDispatcher.addCallback(this@DeliverySelesaiFragment) {
           requireActivity().finish()
        }
        adapterRating = RatingDeliveryAdapter()
        adapterRating.submitList(listOf(1,2,3,4,5))
        binding.rvRating.apply {
            adapter = adapterRating
            binding.rvRating.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.btnOke.setOnClickListener {
            requireActivity().finish()
        }
    }
}