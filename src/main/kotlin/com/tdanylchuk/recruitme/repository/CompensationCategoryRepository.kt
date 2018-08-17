package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationCategoryEntity
import com.tdanylchuk.recruitme.repository.projection.compenstation.CompensationCategoryProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@RepositoryRestResource(path = "compensationCategories", excerptProjection = CompensationCategoryProjection::class)
interface CompensationCategoryRepository : JpaRepository<CompensationCategoryEntity, Long> {

    fun findByCategory(category: String): CompensationCategoryEntity
}