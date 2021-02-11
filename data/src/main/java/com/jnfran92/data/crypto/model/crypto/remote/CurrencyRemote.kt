package com.jnfran92.data.crypto.model.crypto.remote

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [CryptoRemote], needed for getting the price in dollars of each crypto-currency.
 */
data class CurrencyRemote(
    @SerializedName("price") var price: Double,
    @SerializedName("market_cap") var marketCap: Double,
    @SerializedName("volume_24h") var volume24h: Double,
    @SerializedName("percent_change_1h") var percentChange1h: Double,
    @SerializedName("percent_change_24h") var percentChange24h: Double,
    @SerializedName("percent_change_7d") var percentChange7d: Double,
    @SerializedName("last_updated") var lastUpdated: String
)
