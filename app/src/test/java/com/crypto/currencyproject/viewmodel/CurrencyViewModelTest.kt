package com.crypto.currencyproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.crypto.currencyproject.domain.model.Currency
import com.crypto.currencyproject.domain.usecase.ClearDatabaseUseCase
import com.crypto.currencyproject.domain.usecase.GetCurrenciesUseCase
import com.crypto.currencyproject.domain.usecase.InsertCurrenciesUseCase
import com.crypto.currencyproject.ui.viewmodels.CurrencyViewModel
import com.crypto.currencyproject.domain.utils.CurrencyType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

/**
 * To test the app functionalities through ViewModel i.e. CurrencyViewModel.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    // Mocking the use cases objects.
    private val getCurrenciesUseCase: GetCurrenciesUseCase = mock(GetCurrenciesUseCase::class.java)
    private val insertCurrenciesUseCase: InsertCurrenciesUseCase = mock(InsertCurrenciesUseCase::class.java)
    private val clearDatabaseUseCase: ClearDatabaseUseCase = mock(ClearDatabaseUseCase::class.java)

    private lateinit var viewModel: CurrencyViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Setting up the dispatcher to main, so the coroutines or suspended work can execute immediately for testing purposes.
        Dispatchers.setMain(testDispatcher)

        // Construct the view model with mocked objects.
        viewModel = CurrencyViewModel(getCurrenciesUseCase, insertCurrenciesUseCase, clearDatabaseUseCase)
    }

    @After
    fun tearDown() {
        // Reset the dispatcher so can be freshly available for subsequent tests.
        Dispatchers.resetMain()
    }

    /**
     * To test if all currencies list can be obtained.
     */
    @Test
    fun `loadCurrencies should update currencyList with all currencies`() = runTest {
        // Mock all currencies
        val mockAllCurrencies = listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
            Currency(id = "USD", name = "United States Dollar", symbol = "$")
        )

        // Mock the use case to return all currencies
        whenever(getCurrenciesUseCase.invoke(CurrencyType.All)).thenReturn(mockAllCurrencies)

        // Call loadCurrencies
        viewModel.loadCurrencies()

        // Wait for the coroutine to finish
        advanceUntilIdle()

        // Assert that the currencyList is updated
        val currencyList = viewModel.currencyList.first()
        assert(currencyList == mockAllCurrencies)
    }

    /**
     * Test obtaining the crypto currencies.
     */
    @Test
    fun `loadCryptoCurrencies should update currencyList with crypto currencies`() = runTest {
        val mockCryptoCurrencies = listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC")
        )

        whenever(getCurrenciesUseCase.invoke(CurrencyType.Crypto)).thenReturn(mockCryptoCurrencies)

        viewModel.loadCryptoCurrencies()

        advanceUntilIdle()

        val currencyList = viewModel.currencyList.first()
        assert(currencyList == mockCryptoCurrencies)
    }

    /**
     * Test obtaining the fiat currencies.
     */
    @Test
    fun `loadFiatCurrencies should update currencyList with fiat currencies`() = runTest {
        val mockFiatCurrencies = listOf(
            Currency(id = "USD", name = "United States Dollar", symbol = "$")
        )

        whenever(getCurrenciesUseCase.invoke(CurrencyType.Fiat)).thenReturn(mockFiatCurrencies)

        viewModel.loadFiatCurrencies()

        advanceUntilIdle()

        val currencyList = viewModel.currencyList.first()
        assert(currencyList == mockFiatCurrencies)
    }

    /***
     * Test if the currency insertion works.
     */
    @Test
    fun `insertCurrencies should insert currencies and refresh the list`() = runTest {
        val mockInsertedCurrencies = listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
            Currency(id = "USD", name = "United States Dollar", symbol = "$")
        )

        // Mock insert use case
        whenever(insertCurrenciesUseCase.invoke(mockInsertedCurrencies)).thenReturn(Unit)

        // Mock get all currencies to return inserted currencies
        whenever(getCurrenciesUseCase.invoke(CurrencyType.All)).thenReturn(mockInsertedCurrencies)

        // Call insertCurrencies
        viewModel.insertCurrencies(mockInsertedCurrencies)

        advanceUntilIdle()

        // Assert that the list is updated after insertion
        val currencyList = viewModel.currencyList.first()
        assert(currencyList == mockInsertedCurrencies)
    }

    /**
     * To test clear currencies.
     */
    @Test
    fun `clearCurrencies should clear the currencyList`() = runTest {
        viewModel.clearCurrencies()

        advanceUntilIdle()

        val currencyList = viewModel.currencyList.first()
        assert(currencyList.isEmpty())
    }

    /**
     * To test search functionality.
     */
    @Test
    fun `searchCurrencies should filter the currencyList based on the search string`() = runTest {
        // Mock all currencies
        val mockAllCurrencies = listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
            Currency(id = "USD", name = "United States Dollar", symbol = "$")
        )

        whenever(getCurrenciesUseCase.invoke(CurrencyType.All)).thenReturn(mockAllCurrencies)

        viewModel.loadCurrencies()

        advanceUntilIdle()

        // Search for "Bit"
        viewModel.searchCurrencies("Bit")

        advanceUntilIdle()

        // Assert that only Bitcoin is in the filtered list
        val filteredList = viewModel.currencyList.first()
        assert(filteredList.size == 1)
        assert(filteredList[0].name == "Bitcoin")
    }

    /**
     * Test if reset search works.
     */
    @Test
    fun `resetSearch should reset the currencyList to all currencies`() = runTest {
        // Mock all currencies
        val mockAllCurrencies = listOf(
            Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
            Currency(id = "USD", name = "United States Dollar", symbol = "$")
        )

        whenever(getCurrenciesUseCase.invoke(CurrencyType.All)).thenReturn(mockAllCurrencies)

        viewModel.loadCurrencies()

        advanceUntilIdle()

        // Perform a search to filter the list
        viewModel.searchCurrencies("USD")

        advanceUntilIdle()

        var filteredList = viewModel.currencyList.first()
        assert(filteredList.size == 1)

        // Reset the search
        viewModel.resetSearch()

        advanceUntilIdle()

        // Assert that the list is reset to all currencies
        filteredList = viewModel.currencyList.first()
        assert(filteredList == mockAllCurrencies)
    }
}