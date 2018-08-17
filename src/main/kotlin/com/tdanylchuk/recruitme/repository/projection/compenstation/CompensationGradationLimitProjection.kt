package com.tdanylchuk.recruitme.repository.projection.compenstation

import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationGradationLimitEntity
import org.springframework.data.rest.core.config.Projection

@Projection(name = "compensationGradationLimit", types = [CompensationGradationLimitEntity::class])
interface CompensationGradationLimitProjection {

    val grade: String

    val limit: Double
}