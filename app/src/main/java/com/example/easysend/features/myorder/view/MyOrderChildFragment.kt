package com.example.easysend.features.myorder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.databinding.FragmentMyOrderChildBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.myorder.adapter.MyOrderAdapter
import com.example.easysend.features.myorder.data.model.Order


class MyOrderChildFragment : Fragment(), Injectable, View.OnClickListener {
    private var position: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0
        val binding = FragmentMyOrderChildBinding
            .inflate(inflater, container, false)
        val list = arrayListOf<Order>()
        if (position == 0){
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", false))
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", false))
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", false))
        }else{
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", true))
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", true))
            list.add(Order("2019-12-12 07:00", "Jl Pasar Turi 21 Surabaya", "No. Order ID1666788","Jl Terusan Buahbatu, Bandung", true))
        }
        val adapter =
            MyOrderAdapter(list)
        subcribeUI(adapter)
        binding.rvContentBookings.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }
        return binding.root
    }

    fun subcribeUI(adapter: MyOrderAdapter){

    }

    override fun onClick(v: View) {
        Toast.makeText(v.context, "Clicked Position: $position", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
        fun newInstance(args: Bundle): MyOrderChildFragment = MyOrderChildFragment().apply { arguments = args }
    }
}
