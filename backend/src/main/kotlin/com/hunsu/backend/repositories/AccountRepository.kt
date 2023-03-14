package com.hunsu.backend.repositories

import com.hunsu.backend.models.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, Long> {
    fun findByUsername(username: String): Account?
}