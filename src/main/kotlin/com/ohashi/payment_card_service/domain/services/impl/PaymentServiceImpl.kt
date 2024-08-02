package com.ohashi.payment_card_service.domain.services.impl

import com.ohashi.payment_card_service.application.configuration.MccValues
import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO
import com.ohashi.payment_card_service.domain.entities.Amount
import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import com.ohashi.payment_card_service.domain.services.PaymentService
import com.ohashi.payment_card_service.resources.repositories.AccountRepository
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl(
    private val mccValues: MccValues,
    private val accountRepository: AccountRepository
) : PaymentService {

    override fun authorization(transactionRequest: TransactionRequestDTO) {
        val amountType = validateTransactionType(transactionRequest.mcc)

        accountRepository.getByAccount(transactionRequest.account)?.let { account ->
            val updatedAmountList = account.amountList
                .map { amount ->
                    if (validateBalance(amount, amountType, transactionRequest.totalAmount)) {
                        amount.copy(
                            value = amount.value - transactionRequest.totalAmount
                        )
                    } else amount
                }

            accountRepository.save(
                account.copy(
                    amountList = updatedAmountList
                )
            )
        }
    }

    private fun validateTransactionType(mcc: String): AmountType {
        return when {
            mccValues.food.contains(mcc) -> AmountType.FOOD
            mccValues.meal.contains(mcc) -> AmountType.MEAL
            else -> AmountType.CASH
        }
    }

    private fun validateBalance(amount: Amount, amountType: AmountType, value: Double) =
        amount.type == amountType && amount.value >= value
}