package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.CommentEntity
import com.tdanylchuk.recruitme.repository.projection.CommentProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "comments", excerptProjection = CommentProjection::class)
interface CommentRepository : JpaRepository<CommentEntity, Long> {

    fun findByTargetId(targetId: Long): List<CommentEntity>
}