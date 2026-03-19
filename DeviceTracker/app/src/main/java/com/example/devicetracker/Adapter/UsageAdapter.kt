package com.example.devicetracker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.devicetracker.R
import com.example.devicetracker.data.local.entity.AppUsageEntity

class UsageAdapter : RecyclerView.Adapter<UsageAdapter.ViewHolder>() {

    private var list = listOf<AppUsageEntity>()

    fun submitList(newList: List<AppUsageEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvAppName)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usage, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvName.text = item.appName
        holder.tvTime.text = "${item.usageMinutes} min"
    }
}