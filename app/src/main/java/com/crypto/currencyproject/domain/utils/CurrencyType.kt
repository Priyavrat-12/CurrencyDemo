package com.crypto.currencyproject.domain.utils

/**
 * Represents different types of currencies to facilitate identification and classification.
 * This sealed class helps in organizing datasets based on currency type.
 */
sealed class CurrencyType {

    /**
     * Represents cryptocurrencies.
     */
    data object Crypto : CurrencyType() // For cryptocurrencies.

    /**
     * Represents fiat currencies.
     */
    data object Fiat : CurrencyType() // For fiat currencies.

    /**
     * Represents all currency types.
     * This is the default option when no specific type is selected.
     */
    data object All : CurrencyType() // Default, for all currencies.
}
