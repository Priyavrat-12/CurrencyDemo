package com.crypto.currencyproject.ui.composablecards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crypto.currencyproject.domain.model.Currency

/**
 * Represents that how each row under currency listing will look like.
 * @param currency to obtain the currency details to display.
 */
@Composable
fun CurrencyRowItem(currency: Currency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 2.5.dp, bottom = 2.5.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 1. Circular avatar.
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black, shape = CircleShape)
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currency.id.first().toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // 2. Currency Details like name and symbol.
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, end = 5.dp)
        ) {

            // 2.a To display name of the currency.
            Text(text = currency.name, style = MaterialTheme.typography.bodyMedium)

            // 2.b Symbol will only be displayed for Fiat currency.
            currency.code?.let {
                Text(
                    text = currency.symbol,
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Divider(
                color = Color.LightGray,
                thickness = 0.5.dp
            )
        }

        // 3. Right most text in each cell
        // For crypto currency this will show the currency code.
        // For fiat currency this will show the symbol.
        Text(
            text = currency.code ?: currency.symbol,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 5.dp),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

        // 4. Right most arrow in each cell.
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Forward Arrow",
            tint = Color.Gray,
            modifier = Modifier
                .size(24.dp)
                .padding(start = 5.dp, end = 5.dp)
        )
    }
}
