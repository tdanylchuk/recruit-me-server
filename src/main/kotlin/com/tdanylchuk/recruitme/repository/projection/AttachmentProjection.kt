package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.AttachmentEntity
import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "attachment", types = [AttachmentEntity::class])
interface AttachmentProjection {

    val id: Long

    val name: String

    val uploadDate: Date
}
