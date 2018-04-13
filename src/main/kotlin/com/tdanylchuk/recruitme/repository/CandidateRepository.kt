package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.model.CandidateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin

@RepositoryRestResource(path = "candidates")
@CrossOrigin
interface CandidateRepository : JpaRepository<CandidateEntity, Long>