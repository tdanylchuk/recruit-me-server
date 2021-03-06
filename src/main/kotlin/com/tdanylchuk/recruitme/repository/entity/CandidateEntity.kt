package com.tdanylchuk.recruitme.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class CandidateEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val email: String?,

        val position: String?)