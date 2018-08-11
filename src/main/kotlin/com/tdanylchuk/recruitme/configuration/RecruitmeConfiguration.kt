package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.repository.CandidateRepository
import com.tdanylchuk.recruitme.repository.EmployeeRepository
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.CandidateEntity
import com.tdanylchuk.recruitme.repository.entity.EmployeeEntity
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.service.RoleConstants
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecruitmeConfiguration {

    @Bean
    fun init(candidateRepository: CandidateRepository,
             userRepository: UserRepository,
             employeeRepository: EmployeeRepository): ApplicationRunner {
        return ApplicationRunner {
            run {
                loadTestCandidates(candidateRepository)
                loadTestUsers(userRepository)
                loadTestEmployees(employeeRepository)
            }
        }
    }

    private fun loadTestEmployees(employeeRepository: EmployeeRepository) {
        val employeeEntity = EmployeeEntity(0, "FirstEmployeeName", "FirstEmployeeSurname",
                "someemail@gmail.com", "BackEnd developer")
        employeeRepository.save(employeeEntity)
        println("Saved employee - $employeeEntity")
    }

    fun loadTestUsers(userRepository: UserRepository) {
        var user = UserEntity(firstName = "Taras",
                lastName = "Danylchuk",
                role = RoleConstants.USER_ROLE,
                password = "1111",
                email = "danyadream@gmail.com")
        user = userRepository.save(user)
        println("Saved user $user")
    }

    fun loadTestCandidates(candidateRepository: CandidateRepository) {
        listOf("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                "Gremlin", "Triumph", "Ford", "Yugo").forEach { name ->
            val candidate = CandidateEntity(
                    firstName = name,
                    lastName = "last name",
                    email = "$name@gmail.com",
                    position = "developer")
            candidateRepository.save(candidate)
        }
        candidateRepository.findAll().forEach { candidate -> println(candidate) }
    }
}
