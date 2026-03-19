package com.example.devicetracker.ui.dashboard

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.app.NotificationCompat
import com.example.devicetracker.Adapter.CategoryAdapter
import com.example.devicetracker.R
import com.example.devicetracker.data.local.entity.AppUsageEntity
import com.example.devicetracker.data.local.model.CategoryUsage
import com.example.devicetracker.ui.block.BlockActivity
import com.example.devicetracker.ui.details.CategoryDetailsActivity
import com.example.devicetracker.ui.settings.SettingsActivity
import com.example.devicetracker.utils.UsageStatsHelper

import com.google.android.gms.ads.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        adapter = CategoryAdapter { category ->
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("category", category)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Settings button
        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        MobileAds.initialize(this)

        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        val helper = UsageStatsHelper(this)
        val realList = helper.getUsageStats()

        val categoryList = groupByCategory(realList)

        Log.d("DATA", "Size = ${categoryList.size}") // debug

        adapter.submitList(categoryList)

        Handler(Looper.getMainLooper()).postDelayed({

            categoryList.forEach {

                if (it.usedMinutes >= it.limitMinutes) {

                    showNotification()

                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(
                            Intent(this, BlockActivity::class.java)
                        )
                    }, 2000)

                    return@postDelayed
                }
            }

        }, 3000)
    }

    private fun groupByCategory(list: List<AppUsageEntity>): List<CategoryUsage> {

        val map = mutableMapOf<String, Long>()

        list.forEach {
            map[it.category] = (map[it.category] ?: 0) + it.usageMinutes
        }

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)

        val result = mutableListOf<CategoryUsage>()

        map.forEach { (category, minutes) ->

            val limit = when (category) {
                "Social" -> prefs.getInt("social_limit", 120)
                "Entertainment" -> prefs.getInt("ent_limit", 60)
                else -> 999
            }

            result.add(
                CategoryUsage(
                    category = category,
                    usedMinutes = minutes,
                    limitMinutes = limit.toLong()
                )
            )
        }

        return result.sortedByDescending { it.usedMinutes }
    }

    private fun showNotification() {

        val channelId = "screen_time_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Screen Time",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Screen Time Limit Reached")
            .setContentText("You have used all your allowed time")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, builder.build())
    }
}