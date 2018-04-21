package com.tdanylchuk.recruitme.configuration;

import com.tdanylchuk.recruitme.repository.CandidateRepository
import com.tdanylchuk.recruitme.repository.UserRepository
import com.tdanylchuk.recruitme.repository.entity.CandidateEntity
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import com.tdanylchuk.recruitme.service.RoleConstants
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecruitmeConfiguration {

    @Bean
    fun init(repository: CandidateRepository,
             userRepository: UserRepository): ApplicationRunner {
        return ApplicationRunner {
            run {
                listOf("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                        "Gremlin", "Triumph", "Ford", "Yugo").forEach { name ->
                    val candidate = CandidateEntity(
                            firstName = name,
                            lastName = "last name",
                            email = "$name@gmail.com",
                            position = "developer")
                    repository.save(candidate)
                }
                repository.findAll().forEach({ candidate -> System.out.println(candidate) })

                var user = UserEntity(firstName = "Taras",
                        lastName = "Danylchuk",
                        role = RoleConstants.USER_ROLE,
                        password = "1111",
                        email = "danyadream@gmail.com")
                user = userRepository.save(user)
                println("Saved user $user")
            }
        }
    }
}
