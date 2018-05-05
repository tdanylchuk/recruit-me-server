package com.tdanylchuk.recruitme.model

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*

data class UserResponse(val email: String,
                        private val pass: String,
                        val role: String,
                        val id: Long,
                        val firstName: String?,
                        val lastName: String?,
                        val registrationDate: Date) : User(email, pass, listOf(SimpleGrantedAuthority(role)))
