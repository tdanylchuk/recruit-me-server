package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.ActivityEntity
import com.tdanylchuk.recruitme.repository.entity.ActivityType
import com.tdanylchuk.recruitme.repository.projection.ActivityProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "activities", excerptProjection = ActivityProjection::class)
interface ActivityRepository : JpaRepository<ActivityEntity, Long> {

    fun findByTargetId(targetId: Long): List<ActivityEntity>

    fun findByTargetIdAndType(targetId: Long, activityType: ActivityType): List<ActivityEntity>
}