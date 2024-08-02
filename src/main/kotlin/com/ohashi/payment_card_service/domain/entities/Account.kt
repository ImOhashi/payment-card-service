package com.ohashi.payment_card_service.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "account")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val accountId: String,
    val balance: List<Amount>
)
