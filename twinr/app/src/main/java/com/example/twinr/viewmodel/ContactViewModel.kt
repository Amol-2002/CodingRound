package com.example.twinr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twinr.data.Contact
import com.example.twinr.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository
): ViewModel() {

    val contacts = repository.contacts

    fun insert(contact: Contact){
        viewModelScope.launch {
            repository.insert(contact)
        }
    }

    fun update(contact: Contact){
        viewModelScope.launch {
            repository.update(contact)  }
    }

    fun delete(contact:Contact){
        viewModelScope.launch{
            repository.delete(contact)
        }
    }
}