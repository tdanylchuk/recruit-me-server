package com.tdanylchuk.recruitme.repository.entity.compensation;

import javax.persistence.*
import javax.persistence.CascadeType.ALL

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["category"])])
class CompensationCategoryEntity(

        @Id
        @GeneratedValue
        val id: Long? = null,

        val category: String,

        @OneToMany(mappedBy = "compensationCategory", cascade = [ALL])
        var gradationLimits: MutableList<CompensationGradationLimitEntity> = ArrayList()
)
