package com.ohashi.payment_card_service.application.dto

import java.util.*

data class TransactionRequestDTO(
    val account: String,
    val totalAmount: Double,
    val mcc: String = "",
    val merchant: String,
)
