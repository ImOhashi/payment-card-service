package com.ohashi.payment_card_service.domain.services

import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO

interface PaymentService {
    fun authorization(transactionRequest: TransactionRequestDTO)
}