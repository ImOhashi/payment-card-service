package com.ohashi.payment_card_service.domain.services.impl

import com.ohashi.payment_card_service.application.configuration.MccValues
import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus
import com.ohashi.payment_card_service.domain.entities.Account
import com.ohashi.payment_card_service.domain.entities.Amount
import com.ohashi.payment_card_service.domain.entities.enums.AmountType
import com.ohashi.payment_card_service.domain.exceptions.InsufficientAmountValueException
import com.ohashi.payment_card_service.domain.exceptions.InvalidAccountException
import com.ohashi.payment_card_service.domain.services.PaymentService
import com.ohashi.payment_card_service.resources.repositories.AccountRepository
import com.ohashi.payment_card_service.resources.repositories.AmountRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl(
    private val mccValues: MccValues,
    private val accountRepository: AccountRepository,
    private val amountRepository: AmountRepository
) : PaymentService {

    override fun authorization(transactionRequest: TransactionRequestDTO): TransactionStatus {
        val amountType = validateTransactionType(transactionRequest.mcc)
        logger.info("Amount type: $amountType")

        val account = getAccount(transactionRequest.account)

        checkSufficientAmount(account, amountType, transactionRequest.totalAmount)?.let {
            logger.info("Debiting value=${transactionRequest.totalAmount} of amount type=${it.type}")

            amountRepository.save(
                it.copy(value = it.value - transactionRequest.totalAmount)
            )

            logger.info("Transaction approved")
            return TransactionStatus.APPROVED
        }

        return TransactionStatus.REJECTED
    }

    private fun getAccount(accountId: String): Account {
        return accountRepository.getByAccount(accountId)
            ?: throw InvalidAccountException("Account not found with account=$accountId").also {
                logger.error("Account not found with account=$accountId")
            }
    }

    private fun checkSufficientAmount(account: Account, type: AmountType, totalAmount: Double): Amount? {
        return account.amountList.find { validateBalance(it, type, totalAmount) }
    }

    private fun throwInsufficientAmountException() {
        logger.error("Insufficient amount value to authorize transaction")
        throw InsufficientAmountValueException("Insufficient amount value to authorize transaction")
    }


    private fun validateTransactionType(mcc: String): AmountType {
        return when {
            mcc.isBlank() -> AmountType.CASH
            mccValues.food.contains(mcc) -> AmountType.FOOD
            mccValues.meal.contains(mcc) -> AmountType.MEAL
            else -> AmountType.CASH
        }
    }

    private fun validateBalance(amount: Amount, amountType: AmountType, value: Double) =
        amount.type == amountType && amount.value >= value

    companion object {
        private val logger: Logger = LogManager.getLogger(PaymentServiceImpl::class.java)
    }
}