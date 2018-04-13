package com.tdanylchuk.recruitme.service

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct

@Service
class StorageService {

    private val log = LoggerFactory.getLogger(this.javaClass.name)
    private val rootLocation = Paths.get("target/upload-dir")

    fun store(file: MultipartFile): Path {
        try {
            val uuid = UUID.randomUUID().toString()
            val path = this.rootLocation.resolve(uuid)
            Files.copy(file.inputStream, path)
            log.info("Saved file[{}] to path[{}].", file.originalFilename, path)
            return path
        } catch (e: Exception) {
            throw RuntimeException("Failed to store file! " + file.originalFilename, e)
        }
    }

    fun loadFile(filename: String): Resource {
        try {
            val file = rootLocation.resolve(filename)
            val resource = UrlResource(file.toUri())
            return if (resource.exists() || resource.isReadable()) {
                resource
            } else {
                throw RuntimeException("FAIL!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("FAIL!", e)
        }

    }

    fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    @PostConstruct
    fun init() {
        try {
            if (Files.notExists(rootLocation)) {
                Files.createDirectory(rootLocation)
            }
        } catch (e: IOException) {
            throw RuntimeException("Could not initialize storage!", e)
        }

    }

}