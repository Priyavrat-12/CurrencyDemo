package com.crypto.currencyproject.di

import android.app.Application
import com.crypto.currencyproject.data.repository.CurrencyRepositoryImpl
import com.crypto.currencyproject.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Module that helps Hilt provide the implementation for the CurrencyRepository interface.
 * Hilt cannot automatically identify the underlying implementation of the CurrencyRepository interface,
 * so this module binds the CurrencyRepositoryImpl class to the CurrencyRepository interface.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class CurrencyAppModule(private val application: Application) {
    /**
     * Binds the CurrencyRepositoryImpl implementation to the CurrencyRepository interface,
     * allowing Hilt to inject the correct implementation wherever CurrencyRepository is needed.
     *
     * @param currencyRepositoryImpl The implementation of CurrencyRepository.
     * @return The CurrencyRepository interface.
     */
    @Binds
    abstract fun provideRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository
}
