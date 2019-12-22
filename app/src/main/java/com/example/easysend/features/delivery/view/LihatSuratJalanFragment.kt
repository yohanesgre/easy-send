package com.example.easysend.features.delivery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.easysend.databinding.FragmentDeliveryLihatSuratJalanBinding
import com.example.easysend.di.Injectable

class LihatSuratJalanFragment: Fragment(), Injectable {

    private lateinit var binding:FragmentDeliveryLihatSuratJalanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryLihatSuratJalanBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    fun initUI(){
        binding.appBarLayout.toolbar.title = "Surat Jalan"
        (activity as AppCompatActivity).setSupportActionBar(binding.appBarLayout.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().onBackPressedDispatcher.addCallback(this@LihatSuratJalanFragment) {
            binding.root.findNavController().popBackStack()
        }
    }
}