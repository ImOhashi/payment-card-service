package com.ohashi.payment_card_service.domain.services

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ohashi.payment_card_service.application.configuration.MccValues
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus
import com.ohashi.payment_card_service.domain.exceptions.InsufficientAmountValueException
import com.ohashi.payment_card_service.domain.services.impl.PaymentServiceImpl
import com.ohashi.payment_card_service.factories.AccountFactory
import com.ohashi.payment_card_service.factories.AmountFactory
import com.ohashi.payment_card_service.factories.TransactionRequestDTOFactory
import com.ohashi.payment_card_service.resources.repositories.AccountRepository
import com.ohashi.payment_card_service.resources.repositories.AmountRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PaymentServiceImplTest {

    private val mccValuesMock = MccValues(
        "5411,5412",
        "5811,5812"
    )
    private val accountRepositoryMock = mockk<AccountRepository>()
    private val amountRepositoryMock = mockk<AmountRepository>()

    private val service = PaymentServiceImpl(
        mccValuesMock,
        accountRepositoryMock,
        amountRepositoryMock
    )

    @BeforeEach
    fun init() = clearAllMocks()

    @Test
    fun `it should be authorization food transaction with success`() {
        val transactionRequestDTOMock = TransactionRequestDTOFactory.sample()
        val accountMock = AccountFactory.sample()
        val amountMock = AmountFactory.sample()

        every {
            accountRepositoryMock.getByAccount(any())
        } returns accountMock

        every {
            amountRepositoryMock.save(any())
        } returns amountMock

        val result = service.authorization(transactionRequestDTOMock)

        verify(exactly = 1) {
            accountRepositoryMock.getByAccount(any())
            amountRepositoryMock.save(any())
        }

        assertThat(result).isEqualTo(TransactionStatus.APPROVED)
    }

    @Test
    fun `it should be authorization food transaction with success but with cash amount`() {
        val transactionRequestDTOMock = TransactionRequestDTOFactory.sample(totalAmount = 250.toDouble())
        val accountMock = AccountFactory.sample()
        val amountMock = AmountFactory.sample()

        every {
            accountRepositoryMock.getByAccount(any())
        } returns accountMock

        every {
            amountRepositoryMock.save(any())
        } returns amountMock

        val result = service.authorization(transactionRequestDTOMock)

        verify(exactly = 1) {
            accountRepositoryMock.getByAccount(any())
            amountRepositoryMock.save(any())
        }

        assertThat(result).isEqualTo(TransactionStatus.APPROVED)
    }

    @Test
    fun `it should be authorization food transaction with reject`() {
        val transactionRequestDTOMock = TransactionRequestDTOFactory.sample(totalAmount = 2500.toDouble())
        val accountMock = AccountFactory.sample()

        every {
            accountRepositoryMock.getByAccount(any())
        } returns accountMock

        assertThrows<InsufficientAmountValueException> {
            service.authorization(transactionRequestDTOMock)
        }
    }
}