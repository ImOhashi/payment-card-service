package com.ohashi.payment_card_service.domain.entities

import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
data class Amount(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val type: AmountType,
    val value: Double,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account
)