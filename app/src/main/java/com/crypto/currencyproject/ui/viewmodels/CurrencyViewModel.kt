package com.crypto.currencyproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currencyproject.domain.utils.CurrencyType
import com.crypto.currencyproject.domain.model.Currency
import com.crypto.currencyproject.domain.usecase.ClearDatabaseUseCase
import com.crypto.currencyproject.domain.usecase.GetCurrenciesUseCase
import com.crypto.currencyproject.domain.usecase.InsertCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel that acts as a coordinator between the UI layer and the domain layer for currency management.
 * It manages the state of the currency data and provides methods for data operations.
 */
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val insertCurrenciesUseCase: InsertCurrenciesUseCase,
    private val clearDatabaseUseCase: ClearDatabaseUseCase
) : ViewModel() {

    private val _currencyList = MutableStateFlow<List<Currency>>(emptyList())
    val currencyList: StateFlow<List<Currency>> get() = _currencyList

    private var allCurrencies: List<Currency> = emptyList()

    /**
     * Loads all currencies from the data source and updates the currency list.
     */
    fun loadCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            allCurrencies = getCurrenciesUseCase(CurrencyType.All)
            _currencyList.value = allCurrencies
        }
    }

    /**
     * Loads only cryptocurrency data and updates the currency list.
     */
    fun loadCryptoCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            allCurrencies = getCurrenciesUseCase(CurrencyType.Crypto)
            _currencyList.value = allCurrencies
        }
    }

    /**
     * Loads only fiat currency data and updates the currency list.
     */
    fun loadFiatCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            allCurrencies = getCurrenciesUseCase(CurrencyType.Fiat)
            _currencyList.value = allCurrencies
        }
    }

    /**
     * Inserts a list of currencies into the database and refreshes the currency list.
     *
     * @param currencies A list of currencies to be inserted. By default, combines both crypto and fiat currencies.
     */
    fun insertCurrencies(currencies: List<Currency> = getCryptoCurrencies() + getFiatCurrencies()) {
        viewModelScope.launch(Dispatchers.IO) {
            insertCurrenciesUseCase(currencies)
            loadCurrencies() // Refresh the currency list after insertion
        }
    }

    /**
     * Clears all persisted currencies from the database and resets the currency list.
     */
    fun clearCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            clearDatabaseUseCase()
            allCurrencies = emptyList()
            _currencyList.value = emptyList()
        }
    }

    /**
     * Filters the currency list based on the provided search string.
     *
     * @param searchString The string used to filter currencies.
     */
    fun searchCurrencies(searchString: String) {
        val filteredList = allCurrencies.filter { currency ->
            currency.name.startsWith(searchString, ignoreCase = true) ||
                    currency.name.contains(" $searchString", ignoreCase = true) ||
                    currency.symbol.startsWith(searchString, ignoreCase = true)
        }
        _currencyList.value = filteredList
    }

    /**
     * Resets the currency list to show all currencies.
     */
    fun resetSearch() {
        _currencyList.value = allCurrencies
    }

    /**
     * Provides a predefined list of cryptocurrency data.
     * TODO: This data can be obtained from user input or a server.
     */
    private fun getCryptoCurrencies() = listOf(
        Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
        Currency(id = "ETH", name = "Ethereum", symbol = "ETH"),
        Currency(id = "XRP", name = "XRP", symbol = "XRP"),
        Currency(id = "BCH", name = "Bitcoin Cash", symbol = "BCH"),
        Currency(id = "LTC", name = "Litecoin", symbol = "LTC"),
        Currency(id = "EOS", name = "EOS", symbol = "EOS"),
        Currency(id = "BNB", name = "Binance Coin", symbol = "BNB"),
        Currency(id = "LINK", name = "Chainlink", symbol = "LINK"),
        Currency(id = "NEO", name = "NEO", symbol = "NEO"),
        Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
        Currency(id = "ONT", name = "Ontology", symbol = "ONT"),
        Currency(id = "CRO", name = "Crypto.com Chain", symbol = "CRO"),
        Currency(id = "CUC", name = "Cucumber", symbol = "CUC"),
        Currency(id = "USDC", name = "USD Coin", symbol = "USDC"),
    )

    /**
     * Provides a predefined list of fiat currency data.
     * TODO: This data can be obtained from user input or a server.
     */
    private fun getFiatCurrencies() = listOf(
        Currency(id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD"),
        Currency(id = "EUR", name = "Euro", symbol = "€", code = "EUR"),
        Currency(id = "GBP", name = "British Pound", symbol = "£", code = "GBP"),
        Currency(id = "HKD", name = "Hong Kong Dollar", symbol = "$", code = "HKD"),
        Currency(id = "JPY", name = "Japanese Yen", symbol = "¥", code = "JPY"),
        Currency(id = "AUD", name = "Australian Dollar", symbol = "$", code = "AUD"),
        Currency(id = "USD", name = "United States Dollar", symbol = "$", code = "USD"),
    )
}
