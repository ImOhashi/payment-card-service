package com.ohashi.payment_card_service.factories

import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO

object TransactionRequestDTOFactory {
    fun sample(totalAmount: Double = 100.toDouble()) = TransactionRequestDTO(
        account = "account",
        totalAmount = totalAmount,
        mcc = "5411",
        merchant = "iFood"
    )
}