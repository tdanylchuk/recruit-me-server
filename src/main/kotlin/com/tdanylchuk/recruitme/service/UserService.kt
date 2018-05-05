package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.exception.ServerException
import com.tdanylchuk.recruitme.model.UserRequest
import com.tdanylchuk.recruitme.model.UserResponse
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.service.RoleConstants.USER_ROLE
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun loadUser(email: String?): UserEntity {
        return userRepository.findByEmail(email)
    }

    fun register(userRequest: UserRequest): Long? {
        val user = convertRequestToEntity(userRequest)
        val savedUser = userRepository.save(user)
        log.info("User[{}] with id[{}] has been registered.", savedUser.email, savedUser.id)
        return savedUser.id
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        log.info("Checking user - [$username]...")
        try {
            val loadUser = loadUser(username)
            return convertEntityToResponse(loadUser)
        } catch (e: EmptyResultDataAccessException) {
            log.warn("User[{}] not found.", username)
            throw UsernameNotFoundException("User not found.")
        }
    }

    private fun convertRequestToEntity(userRequest: UserRequest): UserEntity {
        return UserEntity(
                email = userRequest.email,
                password = userRequest.password,
                firstName = userRequest.firstName,
                lastName = userRequest.lastName,
                role = USER_ROLE)
    }

    private fun convertEntityToResponse(userEntity: UserEntity): UserResponse {
        val userId = userEntity.id ?: throw ServerException("Candidate should have id")
        return UserResponse(
                id = userId,
                email = userEntity.email,
                firstName = userEntity.firstName,
                lastName = userEntity.lastName,
                role = userEntity.role,
                pass = userEntity.password,
                registrationDate = userEntity.registrationDate!!)
    }

}
