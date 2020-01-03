package com.example.easysend.features.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.example.easysend.R
import com.example.easysend.databinding.FragmentHomeBinding
import com.example.easysend.di.Injectable
import com.example.easysend.di.injectViewModel
import com.example.easysend.features.darurat.DaruratDeliveryActivity
import com.example.easysend.features.delivery.DeliveryActivity
import com.example.easysend.features.home.adapter.RatingAdapter
import com.example.easysend.features.home.viewmodel.HomeViewModel
import com.example.easysend.features.notification.NotificationActivity
import com.example.easysend.features.point.PointActivity
import com.example.easysend.features.profile.ProfileEditActivity
import com.example.easysend.features.rating.RatingActivity
import com.example.easysend.network.api.Result
import com.example.easysend.network.response.order.OrderResponse
import com.example.easysend.network.response.profile.ProfileResponse
import com.google.gson.Gson
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        viewModel = injectViewModel(viewModelFactory)
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
        binding.btnSimpan.setOnClickListener {
            MaterialDialog(requireContext()).show {
                icon(R.drawable.ic_warning)
                message(text="Pesanan Anda akan tersimpan di Order Saya")
                positiveButton(text = "OK"){
                    dismiss()
                }
            }
        }
        binding.btnMulai.setOnClickListener {
            MaterialDialog(requireContext()).show {
                icon(R.drawable.ic_warning)
                message(text="Apa Anda yakin ingin memulai kegiatan?")
                positiveButton(text = "YA"){
                    //binding.layoutEmptyPesanan.visibility = View.VISIBLE
                   // binding.layoutPesanan.visibility = View.GONE
                    startActivity(Intent(requireContext(), DeliveryActivity::class.java).apply {
                        putExtra("OrderId", viewModel.selectedOrderId)
                    })
                }
                negativeButton(text="TIDAK") {
                    dismiss()
                }
            }
        }
        binding.ivNotif.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }
        binding.btnEmergency.setOnClickListener {
            val myItems = listOf("LAKA", "Ban Pecah", "Kendaraan Rusak", "HP Lowbat", "Lainnya")
            MaterialDialog(requireContext()).show {
                listItems(items = myItems) { dialog, index, text ->
                    startActivity(Intent(requireActivity(), DaruratDeliveryActivity::class.java))
                }
            }
        }
        binding.tvLblEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileEditActivity::class.java))
        }
        binding.layoutRatingStar.setOnClickListener {
            startActivity(Intent(requireContext(), RatingActivity::class.java))
        }
        binding.layoutPointInfo.setOnClickListener {
            startActivity(Intent(requireContext(), PointActivity::class.java))
        }
    }

    private fun subscribeUI(){
        subscribeProfile()
        subscribeOrderTerbaru()
    }

    private fun subscribeOrderTerbaru(){
        viewModel.resultOrderTerbaru.observe(viewLifecycleOwner){result->
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

    private fun subscribeProfile(){
        viewModel.resultProfile.observe(viewLifecycleOwner){result->
            when(result.status){
                Result.Status.SUCCESS->{
                    if (result.data!!.data!=null){
                        bindViewProfile(result.data.data)
                    }
                }
                Result.Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> TODO()
            }
        }
    }

    private fun bindViewOrderTerbaru(response: OrderResponse){
        binding.apply {
            viewModel.selectedOrderId = response.order_id
            layoutEmptyPesanan.visibility = View.GONE
            layoutPesanan.visibility = View.VISIBLE

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

    private fun bindViewProfile(response: ProfileResponse){
        binding.apply {
            tvOutputNamaDriver.text = response.name
            tvOutputNopol.text = response.driver_truks.no_polisi
        }
    }
}