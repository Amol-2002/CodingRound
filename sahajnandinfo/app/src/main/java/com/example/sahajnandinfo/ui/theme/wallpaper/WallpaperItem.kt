
package com.example.sahajnandinfo.ui.wallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sahajnandinfo.navigation.NavRoutes
import com.example.wallpaperapp.data.model.Wallpaper

@Composable
fun WallpaperItem(item: NavRoutes.Wallpaper) {

    var showDialog by remember { mutableStateOf(false) }

    Column(Modifier.padding(8.dp)) {

        Image(
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = null,
            modifier = Modifier.height(200.dp).fillMaxWidth()
        )

        Row {

            Button(onClick = { /* favourite */ }) {
                Text("Fav")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { showDialog = true }) {
                Text("Set")
            }
        }
    }

    if (showDialog) {
        SetWallpaperDialog { showDialog = false }
    }
}