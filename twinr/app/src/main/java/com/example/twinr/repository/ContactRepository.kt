package com.example.twinr.repository

import com.example.twinr.data.Contact
import com.example.twinr.data.ContactDao

class ContactRepository (private val dao: ContactDao){

    val contacts = dao.getContacts()

    suspend fun insert(contact : Contact){
        dao.insertContact(contact)
    }

    suspend fun update(contact: Contact){
        dao.updateContact(contact)
    }

    suspend fun delete(contact: Contact){
        dao.deleteContact(contact)
    }



}