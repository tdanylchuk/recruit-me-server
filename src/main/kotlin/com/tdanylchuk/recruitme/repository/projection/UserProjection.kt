package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.UserEntity
import org.springframework.data.rest.core.config.Projection
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Projection(name = "user", types = [UserEntity::class])
interface UserProjection {
    val id: Long
    val email: String
    val firstName: String?
    val lastName: String?
    val role: String
}