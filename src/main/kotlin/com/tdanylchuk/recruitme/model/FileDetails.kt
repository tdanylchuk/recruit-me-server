package com.tdanylchuk.recruitme.model

import java.io.File

data class FileDetails(val file: File,
                       val originalFileName: String,
                       val fileSize: Long)