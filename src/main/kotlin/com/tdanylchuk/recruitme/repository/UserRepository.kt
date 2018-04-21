package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.repository.projection.UserProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "users", excerptProjection = UserProjection::class)
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String?): UserEntity
}