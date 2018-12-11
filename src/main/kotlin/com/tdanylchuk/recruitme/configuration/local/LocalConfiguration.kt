package com.tdanylchuk.recruitme.configuration.local;

import com.amazonaws.services.s3.AmazonS3
import com.tdanylchuk.recruitme.service.FileStorageService
import com.tdanylchuk.recruitme.service.file.SystemFileStorageService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("local")
class LocalConfiguration {

    @Bean
    fun s3FileStorageService(s3client: AmazonS3): FileStorageService = SystemFileStorageService()
}
