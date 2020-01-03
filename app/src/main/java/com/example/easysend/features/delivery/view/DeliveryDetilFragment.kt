package com.example.easysend.features.delivery.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.easysend.databinding.FragmentDeliveryDetilBinding
import com.example.easysend.di.Injectable
import com.example.easysend.di.injectViewModel
import com.example.easysend.features.delivery.viewmodel.DeliveryDetailViewModel
import com.example.easysend.network.api.Result
import com.example.easysend.network.response.delivery.OrderDetailResponse
import com.google.gson.Gson
import javax.inject.Inject

class DeliveryDetilFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DeliveryDetailViewModel
    private lateinit var binding: FragmentDeliveryDetilBinding
    private val args: DeliveryDetilFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.id = args.orderId
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
        requireActivity().onBackPressedDispatcher.addCallback(this@DeliveryDetilFragment) {
            binding.root.findNavController().popBackStack()
        }
    }

    private fun subscribeUI() {
        viewModel.resultOrderDetail.observe(viewLifecycleOwner){result->
            when(result.status){
                Result.Status.SUCCESS->{
                    val gson = Gson()
                    Log.d("Result Json:",  gson.toJson(result.data))
                    if (result.data!!.data!=null){
                        bindViewOrderTerbaru(result.data.data)
                    }
                }
                Result.Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> TODO()
            }
        }
    }

    private fun bindViewOrderTerbaru(response: OrderDetailResponse){
        binding.apply {
            tvOutputNoPesanan.text = response.order.id.toString()
            tvOutputLokasiPengambilan.text = response.order.asal_alamat
            tvOutputLokasiPengiriman.text = response.order.order_detail_rutes[response.order.order_detail_rutes.lastIndex].alamat
            tvOutputRuteTambahan.text = ""
            when (response.order.jenis_perjalanan){
                "1x jalan" ->{
                    binding.rbPengirimanSekali.isChecked
                }
                else->{
                    binding.rbPengirimanPp.isChecked
                }
            }
            tvOutputBeratVolumeBarang.text = response.order.estimasi_berat.substringBefore(" ")
            tvOutputJumlahTruck.text = response.order.jumlah_truk
            tvOutputJenisTruck.text = " "
            tvOutputNomorKontainer.text = response.order.container_id.toString()
            tvOutputNamaKapal.text = response.order.nama_kapal
            tvOutputKebutuhanTambahan.text = " "
            tvOutputTanggal.text = response.order.pengiriman_tanggal
            tvOutputWaktu.text = response.order.pengiriman_waktu
            var barang = ""
            for(i in 0..response.order.order_detail_barangs.size-1){
                barang += if (i == response.order.order_detail_barangs.size-1)
                    "${response.order.order_detail_barangs[i].deskripsi}"
                else
                    "${response.order.order_detail_barangs[i].deskripsi},"
            }
            tvOutputDeskripsiBarang.text = barang
            tvOutputNomorPengurus.text = response.order.no_telepon_pengurus
        }
    }
}