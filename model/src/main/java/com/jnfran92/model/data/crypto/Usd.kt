package com.jnfran92.model.data.crypto

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [Crypto], for getting the price in dollars of each crypto-currency.
 */
class Usd (
    @SerializedName("price") var price:Double,
    @SerializedName("market_cap") var marketCap:Double,
    @SerializedName("last_updated") var lastUpdated:String
)
