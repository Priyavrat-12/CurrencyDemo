package com.crypto.currencyproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crypto.currencyproject.data.local.entities.CurrencyInfo
import com.crypto.currencyproject.data.local.dao.CurrencyDao
import com.crypto.currencyproject.data.utils.DB_VERSION

/**
 * Defines the Room database for storing currency information.
 * This database provides access to the currency data via DAOs.
 * Hilt will automatically provide an instance of this database for dependency injection.
 *
 * @see CurrencyDao for database access operations.
 */
@Database(entities = [CurrencyInfo::class], version = DB_VERSION)
abstract class CurrencyAppDatabase : RoomDatabase() {
    /**
     * Provides access to the CurrencyDao for performing CRUD operations on the currency table.
     *
     * @return CurrencyDao instance for interacting with the currency data.
     */
    abstract fun currencyDao(): CurrencyDao
}
