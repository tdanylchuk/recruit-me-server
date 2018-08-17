package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.model.CompensationRequest
import com.tdanylchuk.recruitme.service.CompensationService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("compensations")
class CompensationController(private val compensationService: CompensationService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping("compensate")
    fun compensate(@RequestBody compensationRequest: CompensationRequest) {
        log.info("Compensation request received - [$compensationRequest]")
        compensationService.compensate(compensationRequest)
    }
}