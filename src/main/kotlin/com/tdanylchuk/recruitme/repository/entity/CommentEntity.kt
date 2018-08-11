package com.tdanylchuk.recruitme.repository.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class CommentEntity(
        @Id @GeneratedValue
        val id: Long? = null,

        val content: String,

        val targetId: Long,

        val targetType: TargetType,

        @OneToOne
        val author: UserEntity,

        @field:CreationTimestamp
        val addedDate: Date? = null
)
