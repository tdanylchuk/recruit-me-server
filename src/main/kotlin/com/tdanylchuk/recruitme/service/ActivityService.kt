package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.model.UserResponse
import com.tdanylchuk.recruitme.repository.ActivityRepository
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.ActivityEntity
import com.tdanylchuk.recruitme.repository.entity.ActivityType
import com.tdanylchuk.recruitme.repository.entity.TargetType
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository: ActivityRepository,
                      private val userRepository: UserRepository) {
    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun add(targetId: Long,
            activityType: ActivityType,
            targetType: TargetType,
            content: String?) {

        val author = userRepository.getOne(getAuthorId())
        var entity = ActivityEntity(
                content = content,
                targetId = targetId,
                author = author,
                targetType = targetType,
                type = activityType)
        entity = activityRepository.save(entity)
        log.info("Activity[$entity] has been saved.")
    }

    fun add(targetId: Long,
            activityType: ActivityType,
            targetType: TargetType) {
        add(targetId, activityType, targetType, null)
    }

    fun getAuthorId(): Long {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication.principal as UserResponse).id
    }

}