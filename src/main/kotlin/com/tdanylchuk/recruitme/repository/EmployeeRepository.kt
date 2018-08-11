package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.EmployeeEntity
import com.tdanylchuk.recruitme.repository.projection.EmployeeProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "employees", excerptProjection = EmployeeProjection::class)
interface EmployeeRepository : JpaRepository<EmployeeEntity, Long>