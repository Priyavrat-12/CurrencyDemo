package com.crypto.currencyproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crypto.currencyproject.data.utils.DB_TABLE_NAME

/**
 * Represents the schema of the currency table in the database.
 * Each instance of this data class corresponds to a row in the table.
 * The columns are defined by the fields in this class.
 *
 * @property id The unique identifier for the currency (Primary Key).
 * @property name The display name of the currency (e.g., "Bitcoin").
 * @property symbol The symbol representing the currency (e.g., "BTC").
 * @property code Optional and is for fiat currencies. This is NULL for cryptocurrencies.
 */
@Entity(tableName = DB_TABLE_NAME)
data class CurrencyInfo(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null // Code will be applicable for only fiat currency.
)