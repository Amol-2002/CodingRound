package com.example.twinr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.twinr.data.ContactDatabase
import com.example.twinr.navigation.AppNavGraph
import com.example.twinr.repository.ContactRepository
import com.example.twinr.ui.theme.TwinrTheme
import com.example.twinr.viewmodel.ContactViewModel
import com.example.twinr.viewmodel.ContactViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Room
        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contact_db"
        ).build()

        // Repository
        val repository = ContactRepository(db.contactDao())

        // ViewModel
        val viewModel = ViewModelProvider(this, ContactViewModelFactory(repository)
        )[ContactViewModel::class.java]

        setContent {

            TwinrTheme {

                AppNavGraph(viewModel)

            }
        }
    }
}