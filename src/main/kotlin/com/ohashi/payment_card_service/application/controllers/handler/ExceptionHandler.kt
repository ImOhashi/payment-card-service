package com.ohashi.payment_card_service.application.controllers.handler

import com.ohashi.payment_card_service.application.dto.TransactionResponseDTO
import com.ohashi.payment_card_service.application.dto.enums.TransactionStatus
import com.ohashi.payment_card_service.domain.exceptions.InsufficientAmountValueException
import com.ohashi.payment_card_service.domain.exceptions.InvalidAccountException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(InsufficientAmountValueException::class)
    fun handleInsufficientAmountValueException(insufficientAmountValueException: InsufficientAmountValueException): ResponseEntity<TransactionResponseDTO> {
        return ResponseEntity.ok(TransactionResponseDTO(TransactionStatus.REJECTED.code))
    }

    @ExceptionHandler(InvalidAccountException::class)
    fun handleInvalidAccountException(invalidAccountException: InvalidAccountException): ResponseEntity<TransactionResponseDTO> {
        return ResponseEntity.ok(TransactionResponseDTO(TransactionStatus.PROBLEM.code))
    }
}