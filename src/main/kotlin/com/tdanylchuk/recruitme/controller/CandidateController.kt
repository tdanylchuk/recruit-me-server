package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.service.CandidateService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("candidates")
class CandidateController(private val candidateService: CandidateService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping("/{candidateId}/attachments")
    fun addAttachments(@PathVariable("candidateId") candidateId: Long, @RequestBody attachmentIds: List<Long>) {
        log.info("Adding attachments{} to candidate[{}]...", attachmentIds, candidateId)
        candidateService.addAttachments(candidateId, attachmentIds)
    }

}