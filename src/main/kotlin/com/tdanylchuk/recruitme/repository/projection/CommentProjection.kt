package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.CommentEntity
import com.tdanylchuk.recruitme.repository.entity.TargetType
import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "comment", types = [CommentEntity::class])
interface CommentProjection {

    val id: Long
    val content: String
    val targetId: Long
    val targetType: TargetType
    val author: UserProjection
    val addedDate: Date
}