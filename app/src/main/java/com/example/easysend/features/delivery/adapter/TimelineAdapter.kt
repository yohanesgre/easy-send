package com.example.easysend.features.delivery.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.R
import com.example.easysend.databinding.ItemTimelineBinding
import com.example.easysend.extentions.formatDateTime
import com.example.easysend.extentions.setGone
import com.example.easysend.extentions.setVisible
import com.github.vipulasri.timelineview.TimelineView
import com.github.vipulasri.timelineview.sample.model.OrderStatus
import com.github.vipulasri.timelineview.sample.model.TimeLineModel
import com.github.vipulasri.timelineview.sample.model.TimelineAttributes
import com.github.vipulasri.timelineview.sample.utils.VectorDrawableUtils

/*
class TimelineAdapter(private var tlAttributes: TimelineAttributes) : ListAdapter<TimeLineModel, TimelineAdapter.ViewHolder>(TimeLineModelNewDiffCallback()){
    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeLineModel = getItem(position)
        holder.bind(timeLineModel)
    }

    inner class ViewHolder(val binding: ItemTimelineBinding, viewType: Int) : RecyclerView.ViewHolder(binding.root) {
        fun bind(timeLineModel:TimeLineModel){
            binding.apply{
                when {
                    timeLineModel.status == OrderStatus.INACTIVE -> {
                        timeline.marker = VectorDrawableUtils.getDrawable(itemView.context, R.drawable.ic_marker_inactive, tlAttributes.markerColor)
                    }
                    timeLineModel.status == OrderStatus.ACTIVE -> {
                        timeline.marker = VectorDrawableUtils.getDrawable(itemView.context, R.drawable.ic_marker_active,  tlAttributes.markerColor)
                    }
                    else -> {
                        timeline.setMarker(ContextCompat.getDrawable(itemView.context, R.drawable.ic_marker), tlAttributes.markerColor)
                    }
                }
                if (timeLineModel.date.isNotEmpty()) {
                    textTimelineDate.setVisible()
                    textTimelineDate.text = timeLineModel.date.formatDateTime("yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
                } else
                    textTimelineDate.setGone()
                textTimelineTitle.text = timeLineModel.message
                executePendingBindings()
            }
        }
        init{
            binding.timeline.initLine(viewType)
            binding.timeline.markerSize = tlAttributes.markerSize
            binding.timeline.setMarkerColor(tlAttributes.markerColor)
            binding.timeline.isMarkerInCenter = tlAttributes.markerInCenter
            binding.timeline.markerPaddingLeft = tlAttributes.markerLeftPadding
            binding.timeline.markerPaddingTop = tlAttributes.markerTopPadding
            binding.timeline.markerPaddingRight = tlAttributes.markerRightPadding
            binding.timeline.markerPaddingBottom = tlAttributes.markerBottomPadding
            binding.timeline.linePadding = tlAttributes.linePadding
            binding.timeline.lineWidth = tlAttributes.lineWidth
            binding.timeline.setStartLineColor(tlAttributes.startLineColor, viewType)
            binding.timeline.setEndLineColor(tlAttributes.endLineColor, viewType)
            binding.timeline.lineStyle = tlAttributes.lineStyle
            binding.timeline.lineStyleDashLength = tlAttributes.lineDashWidth
            binding.timeline.lineStyleDashGap = tlAttributes.lineDashGap
        }
    }
}
private class TimeLineModelNewDiffCallback : DiffUtil.ItemCallback<TimeLineModel>() {
    override fun areItemsTheSame(oldItem: TimeLineModel, newItem: TimeLineModel): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: TimeLineModel, newItem: TimeLineModel): Boolean {
        return oldItem == newItem
    }
}*/

class TimelineAdapter(private var listItem: List<TimeLineModel>,
                      private var tlAttributes: TimelineAttributes) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>(){
    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun getItemCount() = listItem.size

    fun updateList(newList:List<TimeLineModel>){
        listItem = newList
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeLineModel = listItem[position]
        holder.bind(timeLineModel)
    }

    inner class ViewHolder(binding: ItemTimelineBinding, viewType: Int) : RecyclerView.ViewHolder(binding.root) {
        val date = binding.textTimelineDate
        val message = binding.textTimelineTitle
        val timeline = binding.timeline
        fun bind(timeLineModel:TimeLineModel){
            when {
                timeLineModel.status == OrderStatus.INACTIVE -> {
                    timeline.marker = VectorDrawableUtils.getDrawable(itemView.context, R.drawable.ic_marker_inactive, tlAttributes.markerColor)
                }
                timeLineModel.status == OrderStatus.ACTIVE -> {
                    timeline.marker = VectorDrawableUtils.getDrawable(itemView.context, R.drawable.ic_marker_active,  tlAttributes.markerColor)
                }
                else -> {
                    timeline.setMarker(ContextCompat.getDrawable(itemView.context, R.drawable.ic_marker), tlAttributes.markerColor)
                }
            }
            if (timeLineModel.date.isNotEmpty()) {
                date.setVisible()
                date.text = timeLineModel.date.formatDateTime("yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
            } else
                date.setGone()
            message.text = timeLineModel.message
        }
        init {
            timeline.initLine(viewType)
            timeline.markerSize = tlAttributes.markerSize
            timeline.setMarkerColor(tlAttributes.markerColor)
            timeline.isMarkerInCenter = tlAttributes.markerInCenter
            timeline.markerPaddingLeft = tlAttributes.markerLeftPadding
            timeline.markerPaddingTop = tlAttributes.markerTopPadding
            timeline.markerPaddingRight = tlAttributes.markerRightPadding
            timeline.markerPaddingBottom = tlAttributes.markerBottomPadding
            timeline.linePadding = tlAttributes.linePadding
            timeline.lineWidth = tlAttributes.lineWidth
            timeline.setStartLineColor(tlAttributes.startLineColor, viewType)
            timeline.setEndLineColor(tlAttributes.endLineColor, viewType)
            timeline.lineStyle = tlAttributes.lineStyle
            timeline.lineStyleDashLength = tlAttributes.lineDashWidth
            timeline.lineStyleDashGap = tlAttributes.lineDashGap
        }
    }
}