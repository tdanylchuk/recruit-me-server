package com.tdanylchuk.recruitme.model

data class CommentRequest(
        val authorId: Long,
        val targetId: Long,
        val content: String
)
