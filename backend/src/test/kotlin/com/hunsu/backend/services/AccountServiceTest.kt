package com.hunsu.backend.services

import com.hunsu.backend.exception.UserException
import com.hunsu.backend.models.Account
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigInteger
import java.security.MessageDigest

@SpringBootTest
@Transactional
class AccountServiceTest(
    @Autowired val accountService: AccountService
) {
    @Test
    fun registerAccount() {
        val account = Account(
            username = "test",
            password = "test1234",
            displayName = "test",
        )

        val created = accountService.register(account)
        assertNotNull(created)

        val found = accountService.getUserByUsername("test")
        assertNotNull(found)

        assertEquals(created?.id, found?.id)
        assertEquals(created?.username, found?.username)
        assertEquals(created?.displayName, found?.displayName)

        // check if password hashed
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(account.password.toByteArray(Charsets.UTF_8))
        val hashed = BigInteger(1, hashedBytes).toString(16)
        assertEquals(created!!.password, hashed)

        // cannot register same username
        assertThrows(UserException::class.java) {
            accountService.register(account)
        }
    }
}