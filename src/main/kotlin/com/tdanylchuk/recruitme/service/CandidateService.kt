package com.tdanylchuk.recruitme.service


import com.tdanylchuk.recruitme.repository.AttachmentRepository
import com.tdanylchuk.recruitme.repository.CandidateRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CandidateService(private val candidateRepository: CandidateRepository,
                       private val attachmentRepository: AttachmentRepository) {

    private var log = LoggerFactory.getLogger(this.javaClass.name)

    fun addAttachments(candidateId: Long, attachmentIds: List<Long>) {
        val candidate = candidateRepository.getOne(candidateId)
        val attachments = attachmentRepository.findByIdIn(attachmentIds)
        val list = ArrayList(attachments)
        list.addAll(candidate.attachments)
        candidate.attachments = list
        candidateRepository.save(candidate)
        log.info("Attachments{} has been assigned to candidate[{}].", attachmentIds, candidateId)
    }

}
