package com.ohashi.payment_card_service.application.dto.enums

enum class TransactionStatus(val code: String) {
    APPROVED("00"),
    REJECTED("51"),
    PROBLEM("07");
}