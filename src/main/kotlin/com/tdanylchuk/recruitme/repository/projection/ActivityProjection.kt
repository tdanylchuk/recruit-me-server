package com.tdanylchuk.recruitme.repository.projection

import com.tdanylchuk.recruitme.repository.entity.ActivityEntity
import com.tdanylchuk.recruitme.repository.entity.ActivityType
import com.tdanylchuk.recruitme.repository.entity.TargetType
import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "activity", types = [ActivityEntity::class])
interface ActivityProjection {

    val id: Long
    val content: String
    val targetId: Long
    val targetType: TargetType
    val author: UserProjection
    val addedDate: Date
    val type: ActivityType
}