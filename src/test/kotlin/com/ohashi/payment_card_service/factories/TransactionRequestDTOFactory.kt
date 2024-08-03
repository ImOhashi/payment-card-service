package com.ohashi.payment_card_service.factories

import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO

object TransactionRequestDTOFactory {
    fun sample() = TransactionRequestDTO(
        account = "account",
        totalAmount = 100.toDouble(),
        mcc = "5411",
        merchant = "iFood"
    )
}