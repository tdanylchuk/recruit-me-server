package com.tdanylchuk.recruitme.repository.entity

import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class EmployeeEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val email: String?,

        val position: String?)