package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.repository.*
import com.tdanylchuk.recruitme.repository.entity.CandidateEntity
import com.tdanylchuk.recruitme.repository.entity.EmployeeEntity
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationCategoryEntity
import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationEntity
import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationGradationLimitEntity
import com.tdanylchuk.recruitme.service.DEFAULT_GRADE
import com.tdanylchuk.recruitme.service.RoleConstants
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecruitmeConfiguration {

    @Bean
    fun init(candidateRepository: CandidateRepository,
             userRepository: UserRepository,
             employeeRepository: EmployeeRepository,
             compensationCategoryRepository: CompensationCategoryRepository,
             compensationRepository: CompensationRepository): ApplicationRunner {
        return ApplicationRunner {
            run {
                loadTestCandidates(candidateRepository)
                loadTestUsers(userRepository)
                loadTestEmployees(employeeRepository)
                loadCompensations(compensationCategoryRepository, compensationRepository)
            }
        }
    }

    private fun loadTestEmployees(employeeRepository: EmployeeRepository) {
        val employeeEntity = EmployeeEntity(
                id = 0,
                firstName = "FirstEmployeeName",
                lastName = "FirstEmployeeSurname",
                email = "someemail@gmail.com",
                position = "BackEnd developer")
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

    fun loadCompensations(compensationCategoryRepository: CompensationCategoryRepository,
                          compensationRepository: CompensationRepository) {
        var sportCategory = CompensationCategoryEntity(
                category = "Sport")
        sportCategory = compensationCategoryRepository.save(sportCategory)

        val sportCompensationLimit = CompensationGradationLimitEntity(
                compensationCategory = sportCategory,
                grade = DEFAULT_GRADE,
                limit = 1000.0)
        sportCategory.gradationLimits.add(sportCompensationLimit)
        compensationCategoryRepository.save(sportCategory)


        var conferenceCategory = CompensationCategoryEntity(
                category = "Conference")
        conferenceCategory = compensationCategoryRepository.save(conferenceCategory)

        val conferenceCompensationLimit = CompensationGradationLimitEntity(
                compensationCategory = conferenceCategory,
                grade = DEFAULT_GRADE,
                limit = 500.0)
        conferenceCategory.gradationLimits.add(conferenceCompensationLimit)
        compensationCategoryRepository.save(conferenceCategory)

        val compensation = CompensationEntity(
                category = "Sport",
                description = "Football",
                amount = 300.0,
                employeeId = 11)
        compensationRepository.save(compensation)
    }
}
