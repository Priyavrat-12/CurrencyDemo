package com.crypto.currencyproject.domain.usecase

import com.crypto.currencyproject.domain.utils.CurrencyType
import com.crypto.currencyproject.domain.model.Currency
import com.crypto.currencyproject.domain.repository.CurrencyRepository
import javax.inject.Inject

/**
 * Use case for retrieving currencies based on the specified currency type (Crypto, Fiat, or All).
 * This class interacts with the repository to fetch the data and maps the results into domain models.
 *
 * @property repository The repository used to fetch the currency data.
 */
class GetCurrenciesUseCase @Inject constructor(private val repository: CurrencyRepository) {

    /**
     * Invokes the use case to fetch a list of currencies based on the given currency type.
     * Maps the database models (CurrencyInfo) to domain models (Currency) for consumption by the UI or other layers.
     *
     * @param currencyType The type of currencies to retrieve (Crypto, Fiat, or All).
     * @return A list of domain-specific Currency objects.
     */
    suspend operator fun invoke(currencyType: CurrencyType): List<Currency> {
        return repository.getCurrencies(currencyType).map {
            Currency(it.id, it.name, it.symbol, it.code)
        }
    }
}
