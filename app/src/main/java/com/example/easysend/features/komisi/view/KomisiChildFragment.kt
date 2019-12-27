package com.example.easysend.features.komisi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.databinding.FragmentKomisiChildBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.komisi.adapter.KomisiAdapter
import com.example.easysend.features.komisi.data.model.KomisiItem


class KomisiChildFragment : Fragment(), Injectable, View.OnClickListener {
    private var position: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0
        val binding = FragmentKomisiChildBinding
            .inflate(inflater, container, false)
        val list = arrayListOf<KomisiItem>()
        if (position == 0){
                list.add(KomisiItem("Jalan Surato Sari, Jakarta", "2019-12-12 07:00", "Rp. 500.000,-", true))
        }
        val adapter =
            KomisiAdapter(list)
        binding.rvContentBookings.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }
        return binding.root
    }

    override fun onClick(v: View) {
        Toast.makeText(v.context, "Clicked Position: $position", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
        fun newInstance(args: Bundle): KomisiChildFragment = KomisiChildFragment().apply { arguments = args }
    }
}
