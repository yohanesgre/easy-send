package com.example.easysend.features.shared

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryDetailDoneBinding
import com.example.easysend.di.Injectable
import com.example.easysend.extentions.dpToPx
import com.example.easysend.extentions.getColorCompat
import com.example.easysend.features.delivery.DeliveryActivity
import com.example.easysend.features.delivery.adapter.TimelineAdapter
import com.github.vipulasri.timelineview.TimelineView
import com.github.vipulasri.timelineview.sample.model.OrderStatus
import com.github.vipulasri.timelineview.sample.model.TimeLineModel
import com.github.vipulasri.timelineview.sample.model.TimelineAttributes

class DetailOrderDoneActivity : AppCompatActivity(), Injectable{

    private val listItemTimeline = ArrayList<TimeLineModel>()
    private lateinit var timelineAdapter: TimelineAdapter
    private lateinit var tlAttributes: TimelineAttributes
    private lateinit var binding:ActivityDeliveryDetailDoneBinding
    var orderId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderId = intent.getIntExtra("orderId", 0)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail_done)
        binding.toolbar.title="Detail Order"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.btnDetilOrder.setOnClickListener {
            startActivity(Intent(this, DeliveryActivity::class.java))
        }
        tlAttributes = TimelineAttributes(
            markerSize = dpToPx(20f),
            markerColor = getColorCompat(R.color.colorPrimary),
            markerInCenter = true,
            markerLeftPadding = dpToPx(0f),
            markerTopPadding = dpToPx(0f),
            markerRightPadding = dpToPx(0f),
            markerBottomPadding = dpToPx(0f),
            linePadding = dpToPx(2f),
            startLineColor = getColorCompat(R.color.colorAccent),
            endLineColor = getColorCompat(R.color.colorAccent),
            lineStyle = TimelineView.LineStyle.NORMAL,
            lineWidth = dpToPx(2f),
            lineDashWidth = dpToPx(4f),
            lineDashGap = dpToPx(2f)
        )
        setDataEmptyItemList()
        initRecyclerView()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setDataEmptyItemList(){
        listItemTimeline.add(TimeLineModel("Start dari Garasi", "2019-12-12 07:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Load", "2019-12-12 09:30", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Selesai Loading", "2019-12-12 12:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Unload 1", "2019-12-12 19:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Selesai Unloading 1", "2019-12-12 20:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Unloading 2", "2019-12-12 21:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Selesai Unloading 2", "2019-12-12 21:30", OrderStatus.COMPLETED))
    }

    private fun initRecyclerView() {
        initAdapter()
        binding.timeline.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            @SuppressLint("LongLogTag")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //if(recyclerView.getChildAt(0).top < 0) dropshadow.setVisible() else dropshadow.setGone()
            }
        })
    }

    private fun initAdapter() {
        timelineAdapter = TimelineAdapter(listItemTimeline, tlAttributes)
        binding.timeline.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailOrderDoneActivity, RecyclerView.VERTICAL, false)
            adapter = timelineAdapter
        }
    }
}