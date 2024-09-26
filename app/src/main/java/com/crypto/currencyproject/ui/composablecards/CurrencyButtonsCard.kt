package com.crypto.currencyproject.ui.composablecards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Data class to represent each button
 */
data class CurrencyUiButtonItem(
    val text: String,
    val action: () -> Unit
)

/**
 * Display the currency action buttons.
 */
@Composable
fun CurrencyButtonCard(
    currencyUiButtonItems: List<CurrencyUiButtonItem>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        // Fill the entire available space
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currencyUiButtonItems.forEach { buttonItem ->
                Button(
                    onClick = buttonItem.action
                ) {
                    Text(text = buttonItem.text)
                }
            }
        }
    }
}
