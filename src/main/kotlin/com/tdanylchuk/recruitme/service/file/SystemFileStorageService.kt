package com.tdanylchuk.recruitme.service.file

import com.tdanylchuk.recruitme.model.FileDetails
import com.tdanylchuk.recruitme.service.FileStorageService
import org.slf4j.LoggerFactory
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct

class SystemFileStorageService : FileStorageService {

    private val log = LoggerFactory.getLogger(this.javaClass.name)
    private val rootLocation = "target/upload-dir"
    private val rootPath = Paths.get(rootLocation)

    override fun store(file: MultipartFile): String {
        try {
            val uuid = UUID.randomUUID().toString()
            val path = Paths.get(rootLocation).resolve(uuid)
            Files.copy(file.inputStream, path)
            log.info("Saved file[{}] to path[{}].", file.originalFilename, path)
            return uuid
        } catch (e: Exception) {
            throw RuntimeException("Failed to store file! ${file.originalFilename}", e)
        }
    }

    override fun load(uuid: String): FileDetails {
        try {
            val file = File(Paths.get(rootLocation, uuid).toUri())
            return if (file.exists() || file.canRead()) {
                FileDetails(file.inputStream(), file.length())
            } else {
                throw RuntimeException("FAILed to load file! $uuid")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("FAIL!", e)
        }
    }

    override fun delete(uuid: String) {
        try {
            File(Paths.get(uuid).toUri()).delete()
        } catch (e: Exception) {
            throw RuntimeException("Failed to delete file!", e)
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootPath.toFile())
    }

    @PostConstruct
    fun init() {
        try {
            if (Files.notExists(rootPath)) {
                Files.createDirectory(rootPath)
            }
        } catch (e: IOException) {
            throw RuntimeException("Could not initialize storage!", e)
        }

    }

}