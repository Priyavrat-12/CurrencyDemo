package com.crypto.currencyproject.ui.composablecards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Display a search bar with having back button and reset button enabled.
 *
 * @param keyword to perform the search.
 * @param searchBarPlaceHolder The place holder text in the search field.
 * @param onQueryChange will be invoked when the search has been modified.
 * @param onClear will be called in case of clearing the search.
 */
@Composable
fun SearchBar(
    keyword: String,
    searchBarPlaceHolder: String = "Search Currency...",
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 1. Back button.
        if (keyword.isNotEmpty()) {
            IconButton(onClick = onClear) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        // 2. Search text field.
        TextField(
            value = keyword,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f),
            placeholder = { Text(searchBarPlaceHolder) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
                unfocusedIndicatorColor = Color.Transparent // Hide the unfocused indicator
            ),
            singleLine = true,
        )

        // 3. Reset search
        if (keyword.isNotEmpty()) {
            IconButton(onClick = onClear) {
                Icon(Icons.Default.Close, contentDescription = "Clear")
            }
        }
    }
}
