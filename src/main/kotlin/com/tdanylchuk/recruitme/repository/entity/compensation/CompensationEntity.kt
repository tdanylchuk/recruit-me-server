package com.tdanylchuk.recruitme.repository.entity.compensation

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class CompensationEntity(
        @Id
        @GeneratedValue
        val id: Long? = null,

        val category: String,

        val amount: Double,

        val employeeId: Long,

        val description: String
)