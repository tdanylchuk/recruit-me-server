package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationEntity
import com.tdanylchuk.recruitme.repository.projection.compenstation.CompensationProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@RepositoryRestResource(path = "compensations", excerptProjection = CompensationProjection::class)
interface CompensationRepository : JpaRepository<CompensationEntity, Long> {

    fun findByEmployeeId(employeeId: Long): List<CompensationEntity>
}