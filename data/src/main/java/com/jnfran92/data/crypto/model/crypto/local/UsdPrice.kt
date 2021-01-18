package com.jnfran92.data.crypto.model.crypto.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(tableName = "usd_prices")
data class UsdPrice(
    @PrimaryKey val usdPriceId: Long?,
    val value: Double,
    val date: String,
    val cryptoLocalId: Long
)