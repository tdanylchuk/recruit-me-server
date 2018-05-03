package com.tdanylchuk.recruitme.service


import com.tdanylchuk.recruitme.model.FileDetails
import com.tdanylchuk.recruitme.repository.AttachmentRepository
import com.tdanylchuk.recruitme.repository.entity.ActivityType
import com.tdanylchuk.recruitme.repository.entity.AttachmentEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AttachmentService(private val attachmentRepository: AttachmentRepository,
                        private val fileStorageService: FileStorageService,
                        private val activityService: ActivityService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun upload(attachmentFile: MultipartFile): Long {
        val path = fileStorageService.store(attachmentFile)
        val attachment = AttachmentEntity(
                path = path.toString(),
                name = attachmentFile.originalFilename ?: "")

        val savedAttachment = attachmentRepository.save(attachment)
        log.info("Attachment[{}] has been saved.", savedAttachment)
        return savedAttachment.id
    }

    fun getDetails(attachmentId: Long): FileDetails {
        val attachment = attachmentRepository.getOne(attachmentId)
        val file = fileStorageService.load(attachment.path)
        return FileDetails(file, attachment.name, file.length())
    }

    fun delete(attachmentId: Long) {
        val attachment = attachmentRepository.getOne(attachmentId)
        fileStorageService.delete(attachment.path)
        attachmentRepository.delete(attachment)
        log.info("Attachment[{}] has been deleted. Path[{}]", attachmentId, attachment.path)
        activityService.add(attachment.candidate!!.id, ActivityType.CANDIDATE_ATTACHMENT_DELETED)
    }


}
