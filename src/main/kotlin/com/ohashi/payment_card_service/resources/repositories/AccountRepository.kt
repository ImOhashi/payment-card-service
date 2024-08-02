package com.ohashi.payment_card_service.resources.repositories

import com.ohashi.payment_card_service.domain.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {
    fun getByAccount(account: String): Account?
}