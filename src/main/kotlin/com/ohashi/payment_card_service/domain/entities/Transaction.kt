package com.ohashi.payment_card_service.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID,
    val accountId: String,
    val amount: Double,
    val merchant: String,
    val mcc: String
)
