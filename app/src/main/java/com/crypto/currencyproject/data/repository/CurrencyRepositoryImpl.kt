package com.crypto.currencyproject.data.repository

import com.crypto.currencyproject.data.local.dao.CurrencyDao
import com.crypto.currencyproject.data.local.entities.CurrencyInfo
import com.crypto.currencyproject.domain.repository.CurrencyRepository
import com.crypto.currencyproject.domain.utils.CurrencyType
import javax.inject.Inject

/**
 * Implementation of the CurrencyRepository interface.
 * This class interacts with the local database via CurrencyDao to fetch, insert, and clear currency data.
 * It determines the data source (crypto, fiat, or all currencies) based on the provided CurrencyType.
 *
 * @property currencyDao The DAO used to access the currency database.
 */
class CurrencyRepositoryImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    CurrencyRepository {

    /**
     * Fetches currencies from the database based on the currency type (crypto, fiat, or all).
     *
     * @param currencyType The type of currencies to fetch (Crypto, Fiat, or All).
     * @return List of currencies based on the requested type.
     */
    override suspend fun getCurrencies(currencyType: CurrencyType) = when (currencyType) {
        is CurrencyType.Crypto -> {
            currencyDao.getCryptoCurrencies()
        }

        is CurrencyType.Fiat -> {
            currencyDao.getFiatCurrencies()
        }

        is CurrencyType.All -> {
            currencyDao.getAllCurrencies()
        }
    }

    /**
     * Inserts a list of currencies into the database.
     * Existing records with the same primary key will be replaced.
     *
     * @param currencies List of CurrencyInfo objects to be inserted.
     */
    override suspend fun insertCurrencies(currencies: List<CurrencyInfo>) =
        currencyDao.insertCurrencies(currencies)

    /**
     * Clears all currency records from the database.
     */
    override suspend fun clearCurrencies() = currencyDao.clearAllCurrencyRecords()
}