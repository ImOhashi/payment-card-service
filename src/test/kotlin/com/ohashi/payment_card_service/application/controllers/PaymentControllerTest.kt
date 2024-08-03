package com.ohashi.payment_card_service.application.controllers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus
import com.ohashi.payment_card_service.domain.services.PaymentService
import com.ohashi.payment_card_service.factories.TransactionRequestDTOFactory
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class PaymentControllerTest {

    private val paymentServiceMock = mockk<PaymentService>()

    private val controller = PaymentController(
        paymentService = paymentServiceMock
    )

    @BeforeEach
    fun init() = clearAllMocks()

    @Test
    fun `it should be return a transaction response dto with success`() {
        val transactionRequestDTOMock = TransactionRequestDTOFactory.sample()

        every {
            paymentServiceMock.authorization(any())
        } returns TransactionStatus.APPROVED

        val result = controller.authorization(transactionRequestDTOMock)

        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body!!.code).isEqualTo(TransactionStatus.APPROVED.code)
    }
}