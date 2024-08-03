package com.ohashi.payment_card_service.factories

import com.ohashi.payment_card_service.domain.entities.Account
import com.ohashi.payment_card_service.domain.entities.Amount
import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import java.util.*

object AccountFactory {

    fun sample(): Account {
        val accountId = UUID.randomUUID()

        return Account(
            accountId,
            "account",
            listOf(
                Amount(
                    UUID.randomUUID(),
                    AmountType.FOOD,
                    150.toDouble(),
                    Account(
                        accountId,
                        "account",
                        listOf()
                    )
                ),
                Amount(
                    UUID.randomUUID(),
                    AmountType.CASH,
                    250.toDouble(),
                    Account(
                        accountId,
                        "account",
                        listOf()
                    )
                )
            )
        )
    }
}