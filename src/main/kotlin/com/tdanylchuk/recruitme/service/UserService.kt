package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.model.UserRequest
import com.tdanylchuk.recruitme.model.UserResponse
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.service.RoleConstants.USER_ROLE
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
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

    fun register(userRequest: UserRequest): UserResponse {
        val user = convertRequestToEntity(userRequest)
        val savedUser = userRepository.save(user)
        log.info("User[{}] with id[{}] has been registered.", savedUser.email, savedUser.id)
        return convertEntityToResponse(savedUser)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        log.info("Checking user - [$username]...")
        try {
            val loadUser = loadUser(username)
            return User(loadUser.email, loadUser.password, listOf(SimpleGrantedAuthority(loadUser.role)))
        } catch (e: EmptyResultDataAccessException) {
            throw UsernameNotFoundException("Incorrect combination of email and password.")
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
        return UserResponse(
                id = userEntity.id,
                email = userEntity.email,
                firstName = userEntity.firstName,
                lastName = userEntity.lastName)
    }

}
