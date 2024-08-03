package com.ohashi.payment_card_service.application.dto

data class TransactionRequestDTO(
    val account: String,
    val totalAmount: Double,
    val mcc: String = "",
    val merchant: String,
)
