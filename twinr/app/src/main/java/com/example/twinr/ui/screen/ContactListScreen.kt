package com.example.twinr.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.twinr.viewmodel.ContactViewModel



@Composable
fun ContactListScreen(viewModel: ContactViewModel) {

    val contacts by viewModel.contacts.collectAsState(
        initial = emptyList()
    )
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold { innerPadding ->

        if (contacts.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No contacts found"
                )

            }

        } else {

            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {

                items(contacts) { contact ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = contact.number,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = contact.name,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = contact.mobile,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(
                                onClick = {
                                    showDialog = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )
                            }

                            IconButton(
                                onClick = {
                                    viewModel.delete(contact)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete"
                                )
                            }

                        }

                    }
                    if (showDialog) {

                        var updatedNumber by remember {
                            mutableStateOf(contact.number)
                        }

                        var updatedName by remember {
                            mutableStateOf(contact.name)
                        }

                        var updatedMobile by remember {
                            mutableStateOf(contact.mobile)
                        }

                        AlertDialog(

                            onDismissRequest = {
                                showDialog = false
                            },

                            title = {
                                Text("Update Contact")
                            },

                            text = {

                                Column {

                                    OutlinedTextField(
                                        value = updatedNumber,
                                        onValueChange = {
                                            updatedNumber = it
                                        },
                                        label = {
                                            Text("Number")
                                        }
                                    )

                                    OutlinedTextField(
                                        value = updatedName,
                                        onValueChange = {
                                            updatedName = it
                                        },
                                        label = {
                                            Text("Name")
                                        }
                                    )

                                    OutlinedTextField(
                                        value = updatedMobile,
                                        onValueChange = {
                                            updatedMobile = it
                                        },
                                        label = {
                                            Text("Mobile")
                                        }
                                    )

                                }

                            },

                            confirmButton = {

                                TextButton(
                                    onClick = {

                                        viewModel.update(
                                            contact.copy(
                                                number = updatedNumber,
                                                name = updatedName,
                                                mobile = updatedMobile
                                            )
                                        )

                                        showDialog = false

                                    }
                                ) {
                                    Text("Update")
                                }

                            },

                            dismissButton = {

                                TextButton(
                                    onClick = {
                                        showDialog = false
                                    }
                                ) {
                                    Text("Cancel")
                                }

                            }

                        )

                    }
                }

            }

        }

    }

}