package com.example.careergrow.data.remote

import com.example.careergrow.data.model.CityRequest
import com.example.careergrow.data.model.CityResponse
import com.example.careergrow.data.model.CompleteProfileRequest
import com.example.careergrow.data.model.CompleteProfileResponse
import com.example.careergrow.data.model.DistrictRequest
import com.example.careergrow.data.model.DistrictResponse
import com.example.careergrow.data.model.LoginRequest
import com.example.careergrow.data.model.LoginResponse
import com.example.careergrow.data.model.ProfessionDetailResponse
import com.example.careergrow.data.model.ProfessionResponse
import com.example.careergrow.data.model.RegisterRequest
import com.example.careergrow.data.model.RegisterResponse
import com.example.careergrow.data.model.StateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login.php")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse


    @POST("Registration.php")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @GET("state.php")
    suspend fun getStates(): List<String>   // 🔥 direct list


    @POST("dist.php")
    suspend fun getDistricts(
        @Body request: DistrictRequest
    ): DistrictResponse   // ✅ correct

    @POST("city.php")
    suspend fun getCities(
        @Body request: CityRequest
    ): CityResponse

    @GET("profession.php")
    suspend fun getProfessions(): ProfessionResponse

    @POST("profession_detail.php")
    suspend fun getProfessionDetails(
        @Body request: Map<String, String>
    ): ProfessionDetailResponse

    @POST("userprofile.php")
    suspend fun updateProfile(
        @Body request: CompleteProfileRequest
    ): CompleteProfileResponse
}

