package com.tdanylchuk.recruitme.configuration.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.tdanylchuk.recruitme.properties.AmazonConfigurationProperties
import com.tdanylchuk.recruitme.service.FileStorageService
import com.tdanylchuk.recruitme.service.file.S3FileStorageService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("amazon")
@EnableConfigurationProperties(AmazonConfigurationProperties::class)
class AwsConfiguration(val properties: AmazonConfigurationProperties) {

    @Bean
    fun s3client(): AmazonS3 {
        val s3Properties = properties.s3
        val credentials = BasicAWSCredentials(s3Properties.accessKey, s3Properties.secretKey)
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_1)
                .build()
    }

    @Bean
    @Primary
    fun s3FileStorageService(s3client: AmazonS3): FileStorageService
            = S3FileStorageService(s3client, properties.s3.bucketName)
}
