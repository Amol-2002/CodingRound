package com.example.devicetracker.ui.settings

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.devicetracker.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val etSocial = findViewById<EditText>(R.id.etSocial)
        val etEntertainment = findViewById<EditText>(R.id.etEntertainment)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)

        etSocial.setText(prefs.getInt("social_limit", 120).toString())
        etEntertainment.setText(prefs.getInt("ent_limit", 60).toString())

        btnSave.setOnClickListener {

            val social = etSocial.text.toString().toIntOrNull() ?: 120
            val ent = etEntertainment.text.toString().toIntOrNull() ?: 60

            prefs.edit()
                .putInt("social_limit", social)
                .putInt("ent_limit", ent)
                .apply()

            finish()
        }
    }
}