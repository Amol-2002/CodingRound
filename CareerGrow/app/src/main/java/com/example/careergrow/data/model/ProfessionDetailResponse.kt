package com.example.careergrow.data.model


    data class ProfessionDetailResponse(
        val status: Boolean,
        val data: List<FieldItem>
    )

    data class FieldItem(
        val label: String,
        val under: String
    )

