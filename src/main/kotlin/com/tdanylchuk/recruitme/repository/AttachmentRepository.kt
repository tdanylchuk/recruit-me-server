package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.model.AttachmentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository : JpaRepository<AttachmentEntity, Long>