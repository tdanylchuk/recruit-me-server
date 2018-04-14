package com.tdanylchuk.recruitme.repository.model

import org.springframework.data.rest.core.config.Projection

@Projection(name = "candidate", types = [CandidateEntity::class])
interface CandidateProjection {

    val id: Long

    val firstName: String

    val lastName: String

    val email: String

    val position: String

    val attachments: List<AttachmentProjection>
}
