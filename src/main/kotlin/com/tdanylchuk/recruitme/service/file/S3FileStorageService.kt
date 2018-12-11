package com.tdanylchuk.recruitme.service.file

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.tdanylchuk.recruitme.model.FileDetails
import com.tdanylchuk.recruitme.service.FileStorageService
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.util.*

class S3FileStorageService(
        private val s3client: AmazonS3,
        private val bucketName: String
) : FileStorageService {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun store(file: MultipartFile): String {
        val uuid = UUID.randomUUID().toString()
        val result = s3client.putObject(bucketName, uuid, file.inputStream, objectMetadata(file))

        log.info("Saved file[{}] to AWS S3 with tag[{}] and key[{}].", file.originalFilename, result.eTag, uuid)
        return uuid
    }

    override fun load(uuid: String): FileDetails {
        val s3Object = s3client.getObject(bucketName, uuid)
        s3Object.objectMetadata.contentLength
        return FileDetails(s3Object.objectContent, s3Object.objectMetadata.contentLength);
    }

    override fun delete(uuid: String) {
        s3client.deleteObject(bucketName, uuid)
    }

    override fun deleteAll() {
        log.warn("Bucket cannot be purged.", bucketName)
    }

    private fun objectMetadata(file: MultipartFile): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = file.contentType
        objectMetadata.contentLength = file.size
        objectMetadata.setHeader("FILE_NAME", file.name)
        objectMetadata.setHeader("ORIGINAL_FILE_NAME", file.originalFilename)
        return objectMetadata
    }

}