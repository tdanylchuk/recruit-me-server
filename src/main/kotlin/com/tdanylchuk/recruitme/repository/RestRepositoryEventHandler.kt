package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.ActivityType
import com.tdanylchuk.recruitme.repository.entity.CandidateEntity
import com.tdanylchuk.recruitme.repository.entity.TargetType
import com.tdanylchuk.recruitme.service.ActivityService
import org.slf4j.LoggerFactory
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterDelete
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler
class RestRepositoryEventHandler(private val activityService: ActivityService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @HandleAfterSave
    fun handleAfterCandidateSave(candidate: CandidateEntity) {
        activityService.add(candidate.id, ActivityType.CANDIDATE_EDITED, TargetType.CANDIDATE)
        log.info("Candidate has been edited: $candidate")
    }

    @HandleAfterCreate
    fun handleAfterCandidateCreate(candidate: CandidateEntity) {
        activityService.add(candidate.id, ActivityType.CANDIDATE_ADDED, TargetType.CANDIDATE)
        log.info("Candidate has been created: $candidate")
    }

    @HandleAfterDelete
    fun handleAfterCandidateDelete(candidate: CandidateEntity) {
        activityService.add(candidate.id, ActivityType.CANDIDATE_DELETED, TargetType.CANDIDATE)
        log.info("Candidate has been deleted: $candidate")
    }

}
