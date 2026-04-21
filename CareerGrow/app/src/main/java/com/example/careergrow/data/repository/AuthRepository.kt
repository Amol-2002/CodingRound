package com.example.careergrow.data.repository

import com.example.careergrow.data.model.DistrictRequest
import com.example.careergrow.data.model.LoginRequest
import com.example.careergrow.data.model.LoginResponse
import com.example.careergrow.data.model.RegisterRequest
import com.example.careergrow.data.model.RegisterResponse
import com.example.careergrow.data.remote.RetrofitInstance

class AuthRepository {

    suspend fun login(mobile: String, password: String): LoginResponse {
        return RetrofitInstance.api.login(
            LoginRequest(mobile, password)
        )
    }


    suspend fun register(fname: String, sname: String, mobile: String, password: String, email: String): RegisterResponse {
        return RetrofitInstance.api.register(
            RegisterRequest(fname, sname, mobile, password, email)
        )

    }

    suspend fun getStates() = RetrofitInstance.api.getStates()

    suspend fun getDistricts(state: String) =
        RetrofitInstance.api.getDistricts(DistrictRequest(state))



}