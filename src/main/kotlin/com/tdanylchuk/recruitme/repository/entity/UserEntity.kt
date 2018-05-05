package com.tdanylchuk.recruitme.repository.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class UserEntity(
        @Id
        @GeneratedValue
        val id: Long? = null,

        val email: String,

        val password: String,

        val firstName: String? = null,

        val lastName: String? = null,

        val role: String,

        @field:CreationTimestamp
        val registrationDate: Date? = null)