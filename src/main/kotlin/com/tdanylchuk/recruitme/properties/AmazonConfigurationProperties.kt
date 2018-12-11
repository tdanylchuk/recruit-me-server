package com.tdanylchuk.recruitme.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("amazon")
class AmazonConfigurationProperties {
    val s3: S3 = S3()
}

class S3 {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var bucketName: String
}