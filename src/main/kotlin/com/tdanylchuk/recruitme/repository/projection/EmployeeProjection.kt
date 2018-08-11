package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.EmployeeEntity
import org.springframework.data.rest.core.config.Projection

@Projection(name = "employee", types = [EmployeeEntity::class])
interface EmployeeProjection {

    val id: Long

    val firstName: String

    val lastName: String

    val email: String

    val position: String
}
