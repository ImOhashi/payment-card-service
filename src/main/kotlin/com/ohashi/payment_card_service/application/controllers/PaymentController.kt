package com.ohashi.payment_card_service.application.controllers

import com.ohashi.payment_card_service.application.dto.TransactionRequestDTO
import com.ohashi.payment_card_service.application.dto.TransactionResponseDTO
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus
import com.ohashi.payment_card_service.domain.services.PaymentService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("payment")
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping("authorization")
    fun authorization(@RequestBody transactionRequestDTO: TransactionRequestDTO): ResponseEntity<TransactionResponseDTO> {
        logger.info("Receiving transaction request with value=${transactionRequestDTO.toString()}")

        return paymentService.authorization(transactionRequestDTO).let { transactionStatus ->
            ResponseEntity.ok(TransactionResponseDTO(transactionStatus.code))
        }
    }

    companion object {
        private val logger: Logger = LogManager.getLogger(PaymentController::class.java)
    }
}