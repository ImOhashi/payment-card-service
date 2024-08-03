package com.ohashi.payment_card_service.domain.exceptions

data class InvalidAccountException(override val message: String): RuntimeException(message)
