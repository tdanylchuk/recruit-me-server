package com.tdanylchuk.recruitme.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Candidate(
        @Id @GeneratedValue val id: Long,
        val name: String,
        val email: String,
        val position: String)