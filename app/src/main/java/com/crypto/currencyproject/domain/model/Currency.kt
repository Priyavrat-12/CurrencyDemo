package com.crypto.currencyproject.domain.model

/**
 * Domain model representing a currency.
 * This model is used in the domain and UI layers after mapping from DTOs.
 *
 * @property id The unique identifier of the currency.
 * @property name The name of the currency (e.g., "Bitcoin").
 * @property symbol The symbol representing the currency (e.g., "BTC").
 * @property code This is optional and null for cryptocurrencies.
 */
data class Currency(
    val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null // Optional, only for fiat currencies
)