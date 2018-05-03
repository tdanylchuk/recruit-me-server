package com.tdanylchuk.recruitme.repository.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
data class ActivityEntity(
        @Id @GeneratedValue
        val id: Long? = null,

        @Lob
        val content: String?,

        val targetId: Long,

        @OneToOne
        val author: UserEntity,

        @field:CreationTimestamp
        val addedDate: Date? = null,

        val type: ActivityType
)
