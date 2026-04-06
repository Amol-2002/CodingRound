
package com.example.sahajnandinfo.ui.wallpaper

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun SetWallpaperDialog(onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Set Wallpaper") },
        text = {
            Column {
                Text("Home Screen")
                Text("Lock Screen")
                Text("Both")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}