package com.ohashi.payment_card_service.domain.exceptions

data class InsufficientAmountValueException(override val message: String) : RuntimeException(message)
