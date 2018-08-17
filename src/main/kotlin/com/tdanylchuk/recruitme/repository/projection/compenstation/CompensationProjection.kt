package com.tdanylchuk.recruitme.repository.projection.compenstation

import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationEntity
import org.springframework.data.rest.core.config.Projection

@Projection(name = "compensation", types = [CompensationEntity::class])
interface CompensationProjection {

    val id: Long

    val category: String

    val amount: Double

    val description: String
}