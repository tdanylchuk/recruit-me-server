package com.tdanylchuk.recruitme.repository.entity

import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
data class AttachmentEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val path: String,

        val name: String,

        @field:UpdateTimestamp
        val uploadDate: Date? = null) {

    @ManyToOne(fetch = FetchType.EAGER)
    var candidate: CandidateEntity? = null
}
