package com.tdanylchuk.recruitme.repository

import com.tdanylchuk.recruitme.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String?): UserEntity
}