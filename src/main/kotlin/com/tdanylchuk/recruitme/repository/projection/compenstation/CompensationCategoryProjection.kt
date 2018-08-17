package com.tdanylchuk.recruitme.repository.projection.compenstation

import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationCategoryEntity
import org.springframework.data.rest.core.config.Projection

@Projection(name = "compensationCategory", types = [CompensationCategoryEntity::class])
interface CompensationCategoryProjection {

    val id: Long

    val category: String

    var gradationLimits: List<CompensationGradationLimitProjection>
}