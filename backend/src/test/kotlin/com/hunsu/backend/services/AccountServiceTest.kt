package com.hunsu.backend.services

import com.hunsu.backend.exception.UserException
import com.hunsu.backend.models.Account
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.jvm.optionals.getOrNull

@SpringBootTest
@Transactional
class AccountServiceTest(
    @Autowired val accountService: AccountService
) {
    @Test
    fun `should create a new account`() {
        val account = Account(
            username = "test",
            password = "test1234",
            displayName = "test",
        )

        val created = accountService.register(account)
        assertThat(created).isNotNull

        val found = accountService.getAccountByUsername("test")
        assertThat(found).isNotNull

        assertThat(created?.id).isEqualTo(found?.id)
        assertThat(created?.username).isEqualTo(found?.username)
        assertThat(created?.displayName).isEqualTo(found?.displayName)

        // check password is correctly hashed
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(account.password.toByteArray(Charsets.UTF_8))
        val hashed = BigInteger(1, hashedBytes).toString(16)
        assertThat(found?.password).isEqualTo(hashed)

        // cannot register same username
        assertThrows(UserException::class.java) {
            accountService.register(account)
        }
    }

    @Test
    fun `should get user by id`(){
        val account = Account(
            username = "test",
            password = "test1234",
            displayName = "test"
        )
        val created = accountService.register(account)
        assertThat(created).isNotNull

        val id = created?.id ?: throw Exception("id is null")
        val acc = accountService.getAccountById(id).getOrNull()
        assertThat(acc).isEqualTo(created)

        val notFound = accountService.getAccountById(1000).getOrNull()
        assertThat(notFound).isNull()
    }
}