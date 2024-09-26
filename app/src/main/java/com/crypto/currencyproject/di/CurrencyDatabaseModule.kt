package com.crypto.currencyproject.di

import android.content.Context
import androidx.room.Room
import com.crypto.currencyproject.data.local.CurrencyAppDatabase
import com.crypto.currencyproject.data.local.dao.CurrencyDao
import com.crypto.currencyproject.data.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides the database and DAO objects for dependency injection.
 * Defines how the CurrencyAppDatabase is constructed and its lifespan within the app.
 * Also provides the CurrencyDao required to interact with the currency data.
 */
@Module
@InstallIn(SingletonComponent::class)
object CurrencyDatabaseModule {
    /**
     * Provides a singleton instance of the CurrencyAppDatabase.
     * The database is constructed using Room's database builder.
     *
     * @param context The application context used to construct the database.
     * @return A singleton instance of CurrencyAppDatabase.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CurrencyAppDatabase {
        return Room.databaseBuilder(context, CurrencyAppDatabase::class.java, DB_NAME).build()
    }

    /**
     * Provides the CurrencyDao for interacting with the currency table.
     * This is used to perform CRUD operations on the database.
     *
     * @param database The CurrencyAppDatabase instance.
     * @return The CurrencyDao for querying the database.
     */
    @Provides
    fun provideCurrencyDao(database: CurrencyAppDatabase): CurrencyDao = database.currencyDao()
}