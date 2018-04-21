package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.model.CommentRequest
import com.tdanylchuk.recruitme.service.CommentService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("comments")
class CommentController(private val commentService: CommentService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping
    fun postComment(@RequestBody commentRequest: CommentRequest) {
        log.info("Posting comment - $commentRequest")
        commentService.post(commentRequest)
    }

}