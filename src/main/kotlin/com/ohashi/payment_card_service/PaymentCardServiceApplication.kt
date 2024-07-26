package com.ohashi.payment_card_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentCardServiceApplication

fun main(args: Array<String>) {
	runApplication<PaymentCardServiceApplication>(*args)
}
