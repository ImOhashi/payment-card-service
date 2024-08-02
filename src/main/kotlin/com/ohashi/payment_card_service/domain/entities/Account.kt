package com.ohashi.payment_card_service.domain.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "account")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val account: String,

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL])
    val amountList: List<Amount>
)
