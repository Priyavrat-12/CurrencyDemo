package com.crypto.currencyproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crypto.currencyproject.data.local.entities.CurrencyInfo
import com.crypto.currencyproject.data.utils.DB_TABLE_NAME

/**
 * This will be the key connector between the currency database and the app.
 * Holds APIs to query or perform various operations on the currency tables.
 */
@Dao
interface CurrencyDao {

    /**
     * Retrieves all currency records from the currency table.
     *
     * @return List of all currency information.
     */
    @Query("SELECT * FROM $DB_TABLE_NAME")
    fun getAllCurrencies(): List<CurrencyInfo>

    /**
     * Retrieves all cryptocurrency records from the currency table.
     * Cryptocurrencies are identified by having a NULL 'code' value.
     *
     * @return List of all crypto currency information.
     */
    @Query("SELECT * FROM $DB_TABLE_NAME WHERE code IS NULL")
    fun getCryptoCurrencies(): List<CurrencyInfo>

    /**
     * Retrieves all fiat currency records from the currency table.
     * Fiat currencies are identified by having a non-NULL 'code' value.
     *
     * @return List of all fiat currency information.
     */
    @Query("SELECT * FROM $DB_TABLE_NAME WHERE code IS NOT NULL")
    fun getFiatCurrencies(): List<CurrencyInfo>

    /**
     * Inserts a list of currencies into the currency table.
     * If a currency with the same primary key already exists, it will be replaced.
     *
     * @param currencies List of CurrencyInfo objects to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyInfo>)

    /**
     * Deletes all records from the currency table, effectively clearing the database.
     */
    @Query("DELETE FROM $DB_TABLE_NAME")
    suspend fun clearAllCurrencyRecords()
}