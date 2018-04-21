package com.tdanylchuk.recruitme.controller

import com.tdanylchuk.recruitme.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController(userService: UserService) {

//    @PostMapping("registration")
//    fun singIn(@RequestBody userRequest: UserRequest) {
//
//    }

    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }

}
