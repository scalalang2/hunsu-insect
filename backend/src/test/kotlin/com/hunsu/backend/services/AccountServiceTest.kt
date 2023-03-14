package com.hunsu.backend.services

import com.hunsu.backend.models.Account
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class AccountServiceTest(
    @Autowired val accountService: AccountService
) {

    @Test
    fun TestRegister() {
        val account = Account(
            username = "test",
            password = "test1234",
            displayName = "test",
        )

        val result = accountService.register(account)
        assertNotNull(result)
    }
}