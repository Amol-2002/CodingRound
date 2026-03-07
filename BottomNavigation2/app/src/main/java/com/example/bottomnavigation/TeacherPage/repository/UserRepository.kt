package com.example.bottomnavigation.TeacherPage.repository



import com.example.bottomnavigation.TeacherPage.api.RetrofitInstance
import com.example.bottomnavigation.TeacherPage.model.User

class UserRepository {

    suspend fun getUsers(): List<User> {

        return RetrofitInstance.api.getUsers().users

    }

}