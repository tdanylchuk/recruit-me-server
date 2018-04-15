package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.service.AttachmentService
import org.slf4j.LoggerFactory
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@CrossOrigin(
        allowedHeaders = ["*"],
        exposedHeaders = ["Content-Length", "Content-Disposition"],
        methods = [GET, DELETE, POST])
@RestController
@RequestMapping("attachments")
class AttachmentController(private val attachmentService: AttachmentService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping("/upload")
    fun upload(@RequestParam("files") files: Array<MultipartFile>): List<Long> {
        return files.map {
            log.info("Uploading attachment[{}]...", it.originalFilename)
            attachmentService.upload(it)
        }
    }

    @GetMapping(path = ["/{attachmentId}/download"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun download(@PathVariable("attachmentId") attachmentId: Long,
                 response: HttpServletResponse): Resource {
        log.info("Downloading attachment[{}]...", attachmentId)
        val details = attachmentService.getDetails(attachmentId)
        response.setHeader("Content-Disposition", "attachment; filename=" + details.originalFileName)
        response.setHeader("Content-Length", details.fileSize.toString())
        return FileSystemResource(details.file)
    }

    @DeleteMapping("/{attachmentId}")
    fun delete(@PathVariable("attachmentId") attachmentId: Long) {
        log.info("Deleting attachment[{}]...", attachmentId)
        attachmentService.delete(attachmentId)
    }

}
