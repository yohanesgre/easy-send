package com.example.easysend.features.delivery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.easysend.databinding.FragmentDeliveryDetilBinding
import com.example.easysend.di.Injectable

class DeliveryDetilFragment : Fragment(), Injectable {
/*
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileDetilViewModel*/
    private lateinit var binding: FragmentDeliveryDetilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // viewModel = injectViewModel(viewModelFactory)
        binding = FragmentDeliveryDetilBinding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun initUI() {
        binding.toolbar.title = "Detail Order"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //binding.toolbar.subtitle = "${args.cariKota}, ${args.cariTanggal}"
        requireActivity().onBackPressedDispatcher.addCallback(this@DeliveryDetilFragment) {
            binding.root.findNavController().popBackStack()
        }
    }

    private fun subscribeUI() {

    }
}