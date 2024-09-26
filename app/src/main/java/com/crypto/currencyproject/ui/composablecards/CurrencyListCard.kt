package com.crypto.currencyproject.ui.composablecards

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.crypto.currencyproject.ui.viewmodels.CurrencyViewModel

/**
 * To construct the complete currency listing screen.
 * This will have the search bar on top of it followed by the currency listing.
 *
 * @param currencyViewModel to query data or take action based on user action such as searching.
 * @param navController to handle the back and fourth navigation.
 */
@Composable
fun CurrencyListCard(currencyViewModel: CurrencyViewModel, navController: NavController) {

    // Handle back press to navigate to the previous screen.
    BackHandler(onBack = {
        navController.popBackStack()  // Navigates back to buttons UI
    })

    // Listen for currency data update.
    val currencies by currencyViewModel.currencyList.collectAsState(initial = emptyList())

    // Remember the search state.
    var searchQuery: String by remember { mutableStateOf("") }

    Column {
        // 1. Search Bar
        SearchBar(
            keyword = searchQuery,
            onQueryChange = { queryString ->
                searchQuery = queryString
                if (searchQuery.isEmpty()) {
                    currencyViewModel.resetSearch() // Reset search if query is empty
                } else {
                    currencyViewModel.searchCurrencies(searchQuery)
                }
            },
            onClear = {
                searchQuery = ""
                currencyViewModel.resetSearch() // Reset search when the clear button is clicked
            }
        )

        // 2. Currency Listing
        if (currencies.isEmpty()) {
            EmptyStateCard() // If the currencies are not available, display the empty state.
        } else {
            // Display the currencies.
            LazyColumn {
                items(currencies.size) { index ->
                    CurrencyRowItem(currency = currencies[index])
                }
            }
        }
    }
}
