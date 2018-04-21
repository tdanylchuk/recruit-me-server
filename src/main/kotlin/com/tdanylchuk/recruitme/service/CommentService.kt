package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.model.CommentRequest
import com.tdanylchuk.recruitme.repository.CommentRepository
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.CommentEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository,
                     private val userRepository: UserRepository) {
    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun post(commentRequest: CommentRequest) {
        val author = userRepository.getOne(commentRequest.authorId)
        var commentEntity = CommentEntity(
                content = commentRequest.content,
                targetId = commentRequest.targetId,
                author = author)
        commentEntity = commentRepository.save(commentEntity)
        log.info("Comment[{}] has been saved", commentEntity.id)
    }

}