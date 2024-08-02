package com.ohashi.payment_card_service.application.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    MccValues::class
)
class PropertiesConfig

@ConfigurationProperties("app.mcc.values")
data class MccValues(
    val food: String,
    val meal: String
)