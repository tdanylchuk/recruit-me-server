package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.AttachmentEntity
import com.tdanylchuk.recruitme.repository.projection.AttachmentProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "attachments", excerptProjection = AttachmentProjection::class)
interface AttachmentRepository : JpaRepository<AttachmentEntity, Long> {

    fun findByIdIn(ids: List<Long>): List<AttachmentEntity>

    fun findByCandidateId(candidateId: Long): List<AttachmentEntity>
}