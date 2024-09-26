package com.crypto.currencyproject.domain.repository

import com.crypto.currencyproject.data.local.entities.CurrencyInfo
import com.crypto.currencyproject.domain.utils.CurrencyType

/**
 * Repository interface for interacting with currency data.
 * Defines the contract for fetching, inserting, and clearing currency records.
 */
interface CurrencyRepository {

    /**
     * Retrieves a list of currencies based on the specified currency type.
     * The default is to return all currencies (crypto and fiat).
     *
     * @param currencyType The type of currencies to retrieve (Crypto, Fiat, or All).
     * @return A list of CurrencyInfo objects matching the requested type.
     */
    suspend fun getCurrencies(currencyType: CurrencyType = CurrencyType.All): List<CurrencyInfo>

    /**
     * Inserts a list of currencies into the database.
     * If a currency with the same primary key exists, it will be replaced.
     *
     * @param currencies A list of CurrencyInfo objects to be inserted.
     */
    suspend fun insertCurrencies(currencies: List<CurrencyInfo>)

    /**
     * Clears all currency records from the database.
     */
    suspend fun clearCurrencies()
}
