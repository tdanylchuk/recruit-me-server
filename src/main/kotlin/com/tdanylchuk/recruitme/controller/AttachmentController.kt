package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.service.AttachmentService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class AttachmentController(private val attachmentService: AttachmentService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping("/upload")
    fun singleFileUpload(@RequestParam("files") files: Array<MultipartFile>): List<Long> {
        return files.map {
            log.info("Uploading file[{}]...", it.originalFilename)
            attachmentService.upload(it)
        }
    }

}
