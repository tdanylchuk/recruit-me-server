package com.tdanylchuk.recruitme.model

class CompensationRequest(
        val employeeId: Long,
        val category: String,
        val amount: Double,
        val description: String
)