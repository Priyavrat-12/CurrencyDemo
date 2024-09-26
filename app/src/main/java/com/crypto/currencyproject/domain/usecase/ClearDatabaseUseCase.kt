package com.crypto.currencyproject.domain.usecase

import com.crypto.currencyproject.domain.repository.CurrencyRepository
import javax.inject.Inject

/**
 * Use case for clearing all currency records from the database.
 * This class handles the execution of the clear operation by interacting with the repository.
 *
 * @property repository The repository used to perform the clear operation.
 */
class ClearDatabaseUseCase @Inject constructor(private val repository: CurrencyRepository) {

    /**
     * Invokes the use case to clear all currency data from the repository.
     * This method is typically called to reset or clean the database.
     */
    suspend operator fun invoke() {
        repository.clearCurrencies()
    }
}
