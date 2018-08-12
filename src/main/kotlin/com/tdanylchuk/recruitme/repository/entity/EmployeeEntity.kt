package com.tdanylchuk.recruitme.repository.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class EmployeeEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val email: String?,

        val position: String? = null,

        val birthday: Date? = null,

        val workStartDate: Date? = null,

        val salary: Int? = null,

        val grade: String? = null,

        val mobile: String? = null,

        val skype: String? = null,

        val profileLink: String? = null
)