package com.tdanylchuk.recruitme.model

import com.tdanylchuk.recruitme.repository.entity.TargetType

data class CommentRequest(
        val authorId: Long,
        val targetId: Long,
        val targetType: TargetType,
        val content: String
)
