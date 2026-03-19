package com.example.devicetracker.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.devicetracker.R
import com.example.devicetracker.data.local.model.CategoryUsage

class CategoryAdapter(
private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var list = listOf<CategoryUsage>()

    fun submitList(newList: List<CategoryUsage>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val progress: ProgressBar = view.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvCategory.text = item.category
        holder.tvTime.text = "${item.usedMinutes}/${item.limitMinutes} min"

        holder.progress.max = item.limitMinutes.toInt()
        holder.progress.progress = item.usedMinutes.toInt()

        // 🔥 CLICK
        holder.itemView.setOnClickListener {
            onClick(item.category)
        }
    }
}