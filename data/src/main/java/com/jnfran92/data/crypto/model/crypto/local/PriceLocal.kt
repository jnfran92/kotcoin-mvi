package com.jnfran92.data.crypto.model.crypto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prices")
data class PriceLocal(
    @PrimaryKey(autoGenerate = true) val usdPriceId: Long?,
    @ColumnInfo( name="price") var price: Double,
    @ColumnInfo( name="market_cap") var marketCap: Double,
    @ColumnInfo( name="volume_24h") var volume24h: Double,
    @ColumnInfo( name="percent_change_1h") var percentChange1h: Double,
    @ColumnInfo( name="percent_change_24h") var percentChange24h: Double,
    @ColumnInfo( name="percent_change_7d") var percentChange7d: Double,
    @ColumnInfo( name="last_updated") var lastUpdated: String,
    @ColumnInfo( name="crypto_id") val cryptoId: Long
)