package com.jnfran92.data.crypto.model.crypto.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity
data class UsdPrice(
    @PrimaryKey val usdPriceId: Long,
    val value: Double,
    val date: Date
)