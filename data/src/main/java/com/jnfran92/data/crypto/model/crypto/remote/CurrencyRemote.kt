package com.jnfran92.data.crypto.model.crypto.remote

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [CryptoRemote], needed for getting the price in dollars of each crypto-currency.
 */
data class CurrencyRemote (
    @SerializedName("price") var price:Double,
    @SerializedName("market_cap") var marketCap:Double,
    @SerializedName("last_updated") var lastUpdated:String
)
