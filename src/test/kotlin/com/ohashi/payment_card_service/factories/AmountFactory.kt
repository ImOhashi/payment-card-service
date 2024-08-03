package com.ohashi.payment_card_service.factories

import com.ohashi.payment_card_service.domain.entities.Account
import com.ohashi.payment_card_service.domain.entities.Amount
import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import java.util.*

object AmountFactory {

    fun sample() = Amount(
        UUID.randomUUID(),
        AmountType.FOOD,
        150.toDouble(),
        AccountFactory.sample()
    )
}