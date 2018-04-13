package com.tdanylchuk.recruitme.service


import com.tdanylchuk.recruitme.repository.AttachmentRepository
import com.tdanylchuk.recruitme.repository.model.AttachmentEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.Instant
import java.util.*

@Service
class AttachmentService(private val attachmentRepository: AttachmentRepository,
                        private val storageService: StorageService) {

    private var log = LoggerFactory.getLogger(this.javaClass.name)

    fun upload(file: MultipartFile): Long {
        val path = storageService.store(file)
        val attachment = AttachmentEntity(
                path = path.toString(),
                name = file.originalFilename ?: "",
                uploadDate = Date.from(Instant.now()))

        val savedAttachment = attachmentRepository.save(attachment)
        log.info("Attachment[{}] has been saved.", savedAttachment)
        return savedAttachment.id
    }

}
