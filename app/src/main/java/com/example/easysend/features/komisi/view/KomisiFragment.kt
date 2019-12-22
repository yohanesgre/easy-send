package com.example.easysend.features.komisi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.MockSamples
import com.example.easysend.databinding.FragmentKomisiBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.komisi.adapter.KomisiAdapter
import com.example.easysend.features.komisi.adapter.viewholder.KomisiViewHolder
import com.example.easysend.features.komisi.data.model.KomisiItem
import com.google.android.material.snackbar.Snackbar

class KomisiFragment : Fragment(), Injectable, KomisiViewHolder.Delegate{

    private val adapter by lazy{KomisiAdapter(this)}
    private lateinit var binding:FragmentKomisiBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKomisiBinding.inflate(inflater, container, false)
        binding.rvKomisi.adapter = adapter
        binding.rvKomisi.layoutManager =LinearLayoutManager(requireContext())

        adapter.addSections(5)
        for (i in 0..5){
            adapter.addItems(i, MockSamples.mockKomisiItems(requireContext(), 5))
        }
        return binding.root
    }

    override fun onItemClick(komisiItem: KomisiItem) {
        Snackbar.make(binding.root, komisiItem.tujuan, Snackbar.LENGTH_SHORT).show()
    }
}