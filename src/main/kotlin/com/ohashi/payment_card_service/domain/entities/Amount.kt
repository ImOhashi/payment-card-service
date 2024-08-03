package com.ohashi.payment_card_service.domain.entities

import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import jakarta.persistence.*
import java.util.UUID

@Entity
data class Amount(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Enumerated(EnumType.STRING)
    val type: AmountType,
    val value: Double,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account
)