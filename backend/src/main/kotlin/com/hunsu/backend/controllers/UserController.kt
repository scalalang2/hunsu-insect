package com.hunsu.backend.controllers

import com.hunsu.backend.services.AccountService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(val accountService : AccountService) {
}