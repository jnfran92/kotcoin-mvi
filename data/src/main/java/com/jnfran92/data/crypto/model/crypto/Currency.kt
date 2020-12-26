package com.jnfran92.data.crypto.model.crypto

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [Crypto], needed for getting the price in dollars of each crypto-currency.
 */
data class Currency (
    @SerializedName("price") var price:Double,
    @SerializedName("market_cap") var marketCap:Double,
    @SerializedName("last_updated") var lastUpdated:String
)
