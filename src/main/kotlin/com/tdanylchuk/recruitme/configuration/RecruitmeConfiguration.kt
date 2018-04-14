package com.tdanylchuk.recruitme.configuration;

import com.tdanylchuk.recruitme.repository.CandidateRepository
import com.tdanylchuk.recruitme.repository.model.CandidateEntity
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecruitmeConfiguration {

    @Bean
    fun init(repository: CandidateRepository): ApplicationRunner {
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
            }
        }
    }
}
