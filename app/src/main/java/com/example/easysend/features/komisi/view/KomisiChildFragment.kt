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
import com.example.easysend.utils.ConvertToCurrency


class KomisiChildFragment : Fragment(), Injectable, View.OnClickListener {
    private var position: Int = 0
    val list = arrayListOf<KomisiItem>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0
        val binding = FragmentKomisiChildBinding
            .inflate(inflater, container, false)
        if (position == 0){
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 500000.0, true))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 200000.0, true))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 600000.0, true))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 400000.0, true))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 700000.0, true))
        }else{
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 500000.0, false))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 200000.0, false))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 600000.0, false))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 400000.0, false))
            list.add(KomisiItem("Jalan Surato Sari, Jakarta", "10/11/2019 07:00 WIB", 700000.0, false))
        }
        calculateKomisi()
        binding.totalKomisi.text = ConvertToCurrency(calculateKomisi(), null)
        val adapter =
            KomisiAdapter(list)
        binding.rvContentBookings.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }
        return binding.root
    }

    private fun calculateKomisi():Double{
        var total = 0.0
        (0 until list.size).forEach { i->
            total += list[i].jumlah
        }
        return total
    }

    override fun onClick(v: View) {
        Toast.makeText(v.context, "Clicked Position: $position", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
        fun newInstance(args: Bundle): KomisiChildFragment = KomisiChildFragment().apply { arguments = args }
    }
}
