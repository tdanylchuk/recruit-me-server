package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.model.Candidate
import com.tdanylchuk.recruitme.repository.CandidateRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class CandidateController(val candidateRepository: CandidateRepository) {

    @GetMapping("all")
    fun getCandidates(): List<Candidate> {
        return candidateRepository.findAll()
    }
}