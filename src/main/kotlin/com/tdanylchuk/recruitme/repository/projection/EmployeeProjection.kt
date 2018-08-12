package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.EmployeeEntity
import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "employee", types = [EmployeeEntity::class])
interface EmployeeProjection {

    val id: Long

    val firstName: String

    val lastName: String

    val email: String

    val position: String

    val birthday: Date?

    val workStartDate: Date?

    val salary: Int?

    val grade: String?

    val mobile: String?

    val skype: String?

    val profileLink: String?
}
