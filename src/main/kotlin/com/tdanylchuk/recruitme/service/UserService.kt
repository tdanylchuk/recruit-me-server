package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.model.UserRequest
import com.tdanylchuk.recruitme.model.UserResponse
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.service.RoleConstants.USER_ROLE
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun loadUser(email: String?): UserEntity? {
        return userRepository.findByEmail(email)
    }

    fun signIn(userRequest: UserRequest): UserResponse {
        val user = convertRequestToEntity(userRequest)
        val savedUser = userRepository.save(user)
        return convertEntityToResponse(savedUser)
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
