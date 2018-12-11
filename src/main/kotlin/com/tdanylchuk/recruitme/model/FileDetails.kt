package com.tdanylchuk.recruitme.model

import java.io.InputStream

data class FileDetails(val inputStream: InputStream,
                       val fileSize: Long)