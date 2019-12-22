package com.example.easysend.features.komisi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.MockSamples
import com.example.easysend.R
import com.example.easysend.databinding.FragmentKomisiBinding
import com.example.easysend.features.komisi.adapter.KomisiAdapter
import com.example.easysend.features.komisi.adapter.viewholder.KomisiViewHolder
import com.example.easysend.features.komisi.data.model.KomisiItem
import com.google.android.material.snackbar.Snackbar

class KomisiActivity : AppCompatActivity(), KomisiViewHolder.Delegate{

    private val adapter by lazy{KomisiAdapter(this)}
    private lateinit var binding:FragmentKomisiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_komisi)
        binding.rvKomisi.adapter = adapter
        binding.rvKomisi.layoutManager =LinearLayoutManager(this)

        adapter.addSections(5)
        for (i in 0..5){
            adapter.addItems(i, MockSamples.mockKomisiItems(this, 5))
        }
    }

    override fun onItemClick(komisiItem: KomisiItem) {
        Snackbar.make(binding.root, komisiItem.tujuan, Snackbar.LENGTH_SHORT).show()
    }
}