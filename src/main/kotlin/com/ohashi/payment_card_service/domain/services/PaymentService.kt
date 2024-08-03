package com.ohashi.payment_card_service.domain.services

import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus

interface PaymentService {
    fun authorization(transactionRequest: TransactionRequestDTO): TransactionStatus
}