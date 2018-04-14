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

        val name: String,

        val email: String,

        val position: String,

        @OneToMany(fetch = EAGER)
        var attachments: MutableList<AttachmentEntity> = ArrayList())