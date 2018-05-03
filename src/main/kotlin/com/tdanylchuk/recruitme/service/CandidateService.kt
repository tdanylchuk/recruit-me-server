package com.tdanylchuk.recruitme.service


import com.tdanylchuk.recruitme.repository.AttachmentRepository
import com.tdanylchuk.recruitme.repository.CandidateRepository
import com.tdanylchuk.recruitme.repository.entity.ActivityType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CandidateService(private val candidateRepository: CandidateRepository,
                       private val attachmentRepository: AttachmentRepository,
                       private val activityService: ActivityService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun addAttachments(candidateId: Long, attachmentIds: List<Long>) {
        val candidate = candidateRepository.getOne(candidateId)
        val attachments = attachmentRepository.findByIdIn(attachmentIds)

        if (attachments.size != attachmentIds.size) {
            throw RuntimeException("Not all attachments$attachmentIds has been found. " +
                    "Found : ${attachments.map { it.id }}")
        }

        attachments.forEach({ it.candidate = candidate })
        candidate.attachments.addAll(attachments)
        candidateRepository.save(candidate)
        activityService.add(candidate.id, ActivityType.CANDIDATE_ATTACHMENT_ADDED)
        log.info("Attachments{} has been assigned to candidate[{}].", attachmentIds, candidateId)
    }

}
