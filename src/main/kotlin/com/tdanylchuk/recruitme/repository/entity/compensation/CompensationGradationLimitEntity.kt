package com.tdanylchuk.recruitme.repository.entity.compensation;

import javax.persistence.*

@Entity
class CompensationGradationLimitEntity(

        @Id
        @GeneratedValue
        val id: Long? = null,

        @ManyToOne
        val compensationCategory: CompensationCategoryEntity,

        val grade: String,

        @Column(name = "limit_column")
        val limit: Double
)
