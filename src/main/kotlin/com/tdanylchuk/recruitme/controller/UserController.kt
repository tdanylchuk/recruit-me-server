package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.model.UserRequest
import com.tdanylchuk.recruitme.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController(private val userService: UserService) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping("/register")
    fun singIn(@RequestBody userRequest: UserRequest) {
        log.info("Registering user [{}]...", userRequest)
        userService.register(userRequest)
    }

    @GetMapping("/user")
    fun user(user: Principal): Principal {
        log.info("User[{}] has been authenticated.", user.name)
        return user
    }

}
