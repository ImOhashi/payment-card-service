package com.ohashi.payment_card_service.resources.repositories

import com.ohashi.payment_card_service.domain.entities.Amount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AmountRepository : JpaRepository<Amount, UUID> {
}