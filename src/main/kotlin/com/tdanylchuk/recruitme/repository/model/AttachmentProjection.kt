package com.tdanylchuk.recruitme.repository.model

import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "attachment", types = [AttachmentEntity::class])
interface AttachmentProjection {

    val id: Long

    val name: String

    val uploadDate: Date
}
