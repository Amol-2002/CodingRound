package com.example.careergrow.data.repository

import com.example.careergrow.data.model.CityRequest
import com.example.careergrow.data.model.CompleteProfileRequest
import com.example.careergrow.data.model.DistrictRequest
import com.example.careergrow.data.remote.RetrofitInstance

class CompleteProfileRepository {


    suspend fun getStates() =
        RetrofitInstance.api.getStates()

    suspend fun getDistricts(state: String) =
        RetrofitInstance.api.getDistricts(DistrictRequest(state))


    suspend fun getCities(state: String, district: String) =
        RetrofitInstance.api.getCities(
            CityRequest(state, district)
        )


    suspend fun getProfessions() =
        RetrofitInstance.api.getProfessions()

    suspend fun getProfessionDetails(profession: String) =
        RetrofitInstance.api.getProfessionDetails(
            mapOf("profession_name" to profession)
        )

    suspend fun updateProfile(request: CompleteProfileRequest) =
        RetrofitInstance.api.updateProfile(request)

}
