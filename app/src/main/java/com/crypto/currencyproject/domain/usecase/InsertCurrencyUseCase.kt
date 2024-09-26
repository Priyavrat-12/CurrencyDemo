package com.crypto.currencyproject.domain.usecase

import com.crypto.currencyproject.data.local.entities.CurrencyInfo
import com.crypto.currencyproject.domain.model.Currency
import com.crypto.currencyproject.domain.repository.CurrencyRepository
import javax.inject.Inject

/**
 * Use case for inserting a list of currencies into the repository.
 *
 * @param repository The repository responsible for managing currency data.
 */
class InsertCurrenciesUseCase @Inject constructor(private val repository: CurrencyRepository) {

    /**
     * Inserts a list of currencies into the repository.
     *
     * @param currencies A list of Currency objects to be inserted.
     *
     * This function maps each Currency object to a CurrencyInfo object
     * before passing the list to the repository for insertion.
     */
    suspend operator fun invoke(currencies: List<Currency>) {
        val currencyInfoList = currencies.map {
            CurrencyInfo(it.id, it.name, it.symbol, it.code)
        }
        repository.insertCurrencies(currencyInfoList)
    }
}
