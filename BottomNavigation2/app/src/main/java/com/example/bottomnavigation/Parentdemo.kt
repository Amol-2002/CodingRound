package com.example.bottomnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Parentdemo(){

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = "Parent Demo", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}