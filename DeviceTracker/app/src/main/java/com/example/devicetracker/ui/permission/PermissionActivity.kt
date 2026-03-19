package com.example.devicetracker.ui.permission

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.devicetracker.R
import com.example.devicetracker.ui.dashboard.DashboardActivity

class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val btnPermission = findViewById<Button>(R.id.btnPermission)

        btnPermission.setOnClickListener {

            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (hasUsagePermission()) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    private fun hasUsagePermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            "android:get_usage_stats",
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }
}