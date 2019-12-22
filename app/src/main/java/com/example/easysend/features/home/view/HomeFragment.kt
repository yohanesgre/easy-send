package com.example.easysend.features.home.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysend.databinding.FragmentHomeBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.delivery.DeliveryActivity
import com.example.easysend.features.home.adapter.RatingAdapter


class HomeFragment : Fragment(), Injectable {
    /*
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel*/

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
       // viewModel = injectViewModel(viewModelFactory)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun initUI() {
        val layoutPointParam = binding.layoutPointInfo.layoutParams
        layoutPointParam.height = binding.layoutRatingStar.layoutParams.height
        binding.layoutPointInfo.layoutParams = layoutPointParam
        val adapterRating = RatingAdapter()
        adapterRating.submitList(listOf(1,2,3,4,5))
        binding.rvRating.apply {
            adapter = adapterRating
            binding.rvRating.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.ivEmptyOrder.setOnClickListener {
            binding.layoutEmptyPesanan.visibility = View.GONE
            binding.layoutPesanan.visibility = View.VISIBLE
        }
        binding.btnMulai.setOnClickListener {
            binding.layoutEmptyPesanan.visibility = View.VISIBLE
            binding.layoutPesanan.visibility = View.GONE
            startActivity(Intent(requireContext(), DeliveryActivity::class.java))
        }
    }
    private fun subscribeUI(){

    }

    private fun showAlertDevelopment() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("Sedang dalam Pengembangan")
            .setMessage("Silakan gunakan fitur lain yang ada...")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
            }
            .create()
            .show()

    }
}