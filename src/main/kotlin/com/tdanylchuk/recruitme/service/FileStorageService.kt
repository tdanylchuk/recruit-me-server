package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.model.FileDetails
import org.springframework.web.multipart.MultipartFile

interface FileStorageService {

    fun store(file: MultipartFile): String
    fun load(uuid: String): FileDetails
    fun delete(uuid: String)
    fun deleteAll()
}