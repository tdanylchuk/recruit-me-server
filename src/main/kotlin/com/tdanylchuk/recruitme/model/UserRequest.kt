package com.tdanylchuk.recruitme.model

import javax.validation.constraints.NotBlank

data class UserRequest(
        @NotBlank
        val email: String,
        @NotBlank
        val password: String,
        @NotBlank
        val firstName: String,
        val lastName: String?
)
