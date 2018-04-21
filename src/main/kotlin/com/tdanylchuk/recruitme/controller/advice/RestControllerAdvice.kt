package com.tdanylchuk.recruitme.controller.advice

import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @ExceptionHandler(EmptyResultDataAccessException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDataIntegrityViolationException(exception: Exception): String {
        log.warn("Entity not found.", exception)
        return "Not found"
    }

}