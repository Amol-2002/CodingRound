package com.example.careergrow.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careergrow.data.model.CompleteProfileRequest
import com.example.careergrow.data.model.FieldItem
import com.example.careergrow.data.repository.CompleteProfileRepository
import kotlinx.coroutines.launch

class CompleteProfileViewModel : ViewModel() {

    private val repo = CompleteProfileRepository()

    // ---------------- COMMON ----------------
    var stateList by mutableStateOf<List<String>>(emptyList())

    // ---------------- PERMANENT ----------------
    var selectedState by mutableStateOf("")
    var districtList by mutableStateOf<List<String>>(emptyList())
    var selectedDistrict by mutableStateOf("")

    var cityList by mutableStateOf<List<String>>(emptyList())
    var selectedCity by mutableStateOf("")

    // ---------------- TEMPORARY ----------------
    var tempState by mutableStateOf("")
    var tempDistrictList by mutableStateOf<List<String>>(emptyList())
    var tempSelectedDistrict by mutableStateOf("")

    var tempCityList by mutableStateOf<List<String>>(emptyList())
    var tempSelectedCity by mutableStateOf("")
    var professionList by mutableStateOf<List<String>>(emptyList())
    var selectedProfession by mutableStateOf("")

    var dynamicFields by mutableStateOf<List<FieldItem>>(emptyList())
    var profileUpdateSuccess by mutableStateOf(false)
    var userStatus by mutableStateOf("")
    var successMessage by mutableStateOf("")

    // ---------------- INIT ----------------

    init {
        getStates()
        getProfessions()
    }

    // ---------------- STATES ----------------
    fun getStates() {
        viewModelScope.launch {
            try {
                stateList = repo.getStates()
            } catch (e: Exception) {
                Log.d("STATE_API", "Error: ${e.message}")
            }
        }
    }

    // ---------------- PERMANENT DISTRICT ----------------
    fun getDistricts(state: String) {
        viewModelScope.launch {
            try {
                val response = repo.getDistricts(state)
                districtList = response.data
                selectedDistrict = ""
                selectedCity = ""
                cityList = emptyList()
            } catch (e: Exception) {
                Log.d("DIST_API", e.message.toString())
            }
        }
    }

    // ---------------- PERMANENT CITY ----------------
    fun getCities(state: String, district: String) {
        viewModelScope.launch {
            try {
                val response = repo.getCities(state, district)
                cityList = response.data
                selectedCity = ""
            } catch (e: Exception) {
                Log.d("CITY_API", e.message.toString())
            }
        }
    }

    // ---------------- TEMP DISTRICT ----------------
    fun getTempDistricts(state: String) {
        viewModelScope.launch {
            try {
                val response = repo.getDistricts(state)
                tempDistrictList = response.data
                tempSelectedDistrict = ""
                tempSelectedCity = ""
                tempCityList = emptyList()
            } catch (e: Exception) {
                Log.d("TEMP_DIST_API", e.message.toString())
            }
        }
    }

    // ---------------- TEMP CITY ----------------
    fun getTempCities(state: String, district: String) {
        viewModelScope.launch {
            try {
                val response = repo.getCities(state, district)
                tempCityList = response.data
                tempSelectedCity = ""
            } catch (e: Exception) {
                Log.d("TEMP_CITY_API", e.message.toString())
            }
        }
    }

    // ---------------- CLEAR TEMP ----------------
    fun clearTempData() {
        tempState = ""
        tempSelectedDistrict = ""
        tempSelectedCity = ""
        tempDistrictList = emptyList()
        tempCityList = emptyList()
    }


    fun getProfessions() {
        viewModelScope.launch {
            try {
                val response = repo.getProfessions()
                professionList = response.data
            } catch (e: Exception) {
                Log.d("PROF_API", e.message.toString())
            }
        }
    }

    fun getProfessionDetails(profession: String) {
        viewModelScope.launch {
            try {
                val response = repo.getProfessionDetails(profession)
                dynamicFields = response.data
            } catch (e: Exception) {
                Log.d("PROF_DETAIL", e.message.toString())
            }
        }
    }




    fun updateProfile(request: CompleteProfileRequest) {
        viewModelScope.launch {
            try {

                val response = repo.updateProfile(request)

                // 🔥 DEBUG LOG
                Log.d("API_RAW_STATUS", response.status)
                Log.d("API_FULL_RESPONSE", response.toString())

                val status = response.status?.trim()?.lowercase() ?: ""

                if (status == "success") {
                    successMessage = response.message
                    profileUpdateSuccess = true
                } else {
                    successMessage = response.message.ifEmpty { "Failed" }
                    profileUpdateSuccess = false
                }

            } catch (e: Exception) {
                successMessage = "API Error: ${e.message}"
                profileUpdateSuccess = false
                Log.d("API_ERROR", e.message.toString())
            }
        }
    }




}