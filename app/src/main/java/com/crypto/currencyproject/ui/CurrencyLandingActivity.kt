package com.crypto.currencyproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.crypto.currencyproject.ui.composablecards.CurrencyUiNavigation
import com.crypto.currencyproject.ui.theme.CurrencyDemoTheme
import com.crypto.currencyproject.ui.viewmodels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyLandingActivity : ComponentActivity() {

    // ViewModel instance for managing currency-related data.
    private val currencyViewModel by viewModels<CurrencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initial UI component to display currency navigation.
                    CurrencyUiNavigation(currencyViewModel)
                }
            }
        }
    }
}
