package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.model.CandidateEntity
import com.tdanylchuk.recruitme.repository.model.CandidateProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin
@RepositoryRestResource(path = "candidates", excerptProjection = CandidateProjection::class)
interface CandidateRepository : JpaRepository<CandidateEntity, Long>