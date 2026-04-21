package com.example.careergrow.data.model

data class CompleteProfileRequest(
    val user_mobile: String,

    val user_fname: String,
    val user_mname: String,
    val user_lname: String,

    val user_avtar: String,
    val birth_date: String,
    val gender: String,

    // Permanent
    val user_per_state: String,
    val user_per_dist: String,
    val user_per_taluka: String,
    val user_per_city: String,
    val user_per_address: String,

    // Temporary
    val user_comm_state: String,
    val user_comm_dist: String,
    val user_comm_tal: String,
    val user_comm_city: String,
    val user_comm_address: String,

    // Profession
    val profession: String,

    // Student
    val std_education: String,
    val std_education_city: String,

    // Employee
    val emp_qualification: String,
    val emp_company_name: String,
    val emp_designation: String,
    val emp_job_city: String,

    // Business
    val business_name: String,
    val business_type: String,
    val business_city: String,
    val business_address: String
)