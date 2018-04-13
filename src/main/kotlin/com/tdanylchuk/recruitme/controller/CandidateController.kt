package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.repository.CandidateRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class CandidateController(val candidateRepository: CandidateRepository) {

}