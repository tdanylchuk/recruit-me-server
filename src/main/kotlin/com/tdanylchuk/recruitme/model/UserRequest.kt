package com.tdanylchuk.recruitme.model

import javax.validation.constraints.NotBlank

class UserRequest(
        @NotBlank
        val email: String,
        @NotBlank
        val password: String,
        val firstName: String?,
        val lastName: String?
)
