package com.tdanylchuk.recruitme.repository.model

import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class CandidateEntity(
        @Id @GeneratedValue
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val email: String,

        val position: String,

        @OneToMany(fetch = EAGER, mappedBy = "candidate", orphanRemoval = true)
        var attachments: MutableList<AttachmentEntity> = ArrayList())