package com.crypto.currencyproject.ui.composablecards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crypto.currencyproject.ui.viewmodels.CurrencyViewModel

/**
 * This class is the origin of app's UI.
 *
 * 1. Initially displays the buttons UI.
 * 2. Handles the navigation between composable.
 */

// Screen names.
private const val CURRENCY_BUTTONS_SCREEN = "currency_action_buttons"
private const val CURRENCY_LIST_SCREEN = "currency_list"

// Button titles.
private const val CLEAR_CURRENCY_TEXT = "Clear Currencies"
private const val INSERT_CURRENCY_TEXT = "Insert Currencies"
private const val CRYPTO_CURRENCY_TEXT = "Crypto Currency"
private const val FIAT_CURRENCY_TEXT = "Fiat Currency"
private const val ALL_CURRENCY_TEXT = "All Currencies"


/**
 * Initially display the buttons UI and provide the required animation.
 * Handle the buttons UI actions.
 *
 * @param currencyViewModel to a perform a particular action based on the button clicks.
 */
@Composable
fun CurrencyUiNavigation(currencyViewModel: CurrencyViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CURRENCY_BUTTONS_SCREEN) {
        // Screen 1: CurrencyButtonCard (Shows buttons to take various actions on currencies)
        composable(CURRENCY_BUTTONS_SCREEN) {

            val currencyUiButtonItems = listOf(

                CurrencyUiButtonItem(CLEAR_CURRENCY_TEXT) { currencyViewModel.clearCurrencies() }, // Clear the complete currency data.

                CurrencyUiButtonItem(INSERT_CURRENCY_TEXT) { currencyViewModel.insertCurrencies() }, // Insert the currency dataset into database.

                CurrencyUiButtonItem(CRYPTO_CURRENCY_TEXT) {
                    currencyViewModel.loadCryptoCurrencies()
                    navController.navigate(CURRENCY_LIST_SCREEN)
                }, // Mark the preference to show Crypto currency.

                CurrencyUiButtonItem(FIAT_CURRENCY_TEXT) {
                    currencyViewModel.loadFiatCurrencies()
                    navController.navigate(CURRENCY_LIST_SCREEN)
                }, // Mark the preference to show the Fiat currency.

                CurrencyUiButtonItem(ALL_CURRENCY_TEXT) {
                    currencyViewModel.loadCurrencies()
                    navController.navigate(CURRENCY_LIST_SCREEN)
                } // To display combined all currencies.
            )

            AnimatedVisibility(visible = true) {
                CurrencyButtonCard(currencyUiButtonItems)
            }
        }

        // Screen 2: CurrencyListCard (Shows the list of currencies)
        composable(CURRENCY_LIST_SCREEN) {
            AnimatedVisibility(visible = true) {
                CurrencyListCard(currencyViewModel, navController)
            }
        }
    }
}
