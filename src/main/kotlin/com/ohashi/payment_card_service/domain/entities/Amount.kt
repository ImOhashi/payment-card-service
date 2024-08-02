package com.ohashi.payment_card_service.domain.entities

import com.ohashi.payment_card_service.domain.entities.enums.AmountType

data class Amount(
    val type: AmountType,
    val value: Double
)