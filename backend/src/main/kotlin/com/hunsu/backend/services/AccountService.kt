package com.hunsu.backend.services

import com.hunsu.backend.exception.UserException
import com.hunsu.backend.models.Account
import com.hunsu.backend.repositories.AccountRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest

@Service
class AccountService(@Autowired val accountRepository: AccountRepository) {
    @Transactional
    fun register(account: Account): Account? {
        val u = accountRepository.findByUsername(account.username)
        if(u != null) {
            throw UserException("Username already exists")
        }

        // hash password with sha256
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(account.password.toByteArray(Charsets.UTF_8))
        val hashed = BigInteger(1, hashedBytes).toString(16)
        return accountRepository.save(account.copy(password = hashed))
    }

    fun getAccountById(id: Long) = accountRepository.findById(id)

    fun getAccountByUsername(username: String) = accountRepository.findByUsername(username)

    @Transactional
    fun updatePassword(id: Long, password: String) {
        val user = accountRepository.findById(id)
        if(user.isEmpty) {
            throw UserException("User not found")
        } else {
            val u = user.get()
            // sha256
            val digest = MessageDigest.getInstance("sha256")
            val hash = digest.digest(password.toByteArray(Charsets.UTF_8)).toString()
            if(u.password != hash) {
                throw UserException("Incorrect password")
            }
            accountRepository.save(user.map { it.copy(password = hash) }.get())
        }
    }
}