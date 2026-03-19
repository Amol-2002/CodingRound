package com.example.devicetracker.ui.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devicetracker.Adapter.UsageAdapter
import com.example.devicetracker.R
import com.example.devicetracker.utils.UsageStatsHelper

class CategoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)

        val category = intent.getStringExtra("category") ?: ""

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = UsageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val helper = UsageStatsHelper(this)
        val list = helper.getUsageStats()

        // 🔥 filter by category
        val filtered = list.filter { it.category == category }

        adapter.submitList(filtered)
    }
}