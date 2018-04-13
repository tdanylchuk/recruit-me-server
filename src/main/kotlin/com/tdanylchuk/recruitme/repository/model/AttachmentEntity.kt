package com.tdanylchuk.recruitme.repository.model

import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class AttachmentEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val path: String,

        val name: String,

        @UpdateTimestamp
        val uploadDate: Date)
