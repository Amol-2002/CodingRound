package com.example.devicetracker.utils

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import com.example.devicetracker.data.local.entity.AppUsageEntity

class UsageStatsHelper(private val context: Context) {

    fun getUsageStats(): List<AppUsageEntity> {

        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        val endTime = System.currentTimeMillis()
        val startTime = endTime - (1000 * 60 * 60 * 24)

        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )

        val result = mutableListOf<AppUsageEntity>()
        val pm = context.packageManager

        stats.forEach {

            val minutes = it.totalTimeInForeground / (1000 * 60)

            if (minutes > 0) {


                val appName = try {
                    val appInfo = pm.getApplicationInfo(it.packageName, 0)
                    pm.getApplicationLabel(appInfo).toString()
                } catch (e: Exception) {
                    it.packageName
                }

                result.add(
                    AppUsageEntity(
                        packageName = it.packageName,
                        appName = appName,
                        category = getCategory(it.packageName),
                        usageMinutes = minutes
                    )
                )
            }
        }

        return result
    }




    private fun getCategory(packageName: String): String {
        return when {
            packageName.contains("instagram") -> "Social"
            packageName.contains("facebook") -> "Social"
            packageName.contains("whatsapp") -> "Social"

            packageName.contains("youtube") -> "Entertainment"
            packageName.contains("netflix") -> "Entertainment"

            else -> "Other"
        }
    }
}