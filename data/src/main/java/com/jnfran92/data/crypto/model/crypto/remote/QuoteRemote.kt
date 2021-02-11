package com.jnfran92.data.crypto.model.crypto.remote

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [CryptoRemote]
 */
data class QuoteRemote(
    @SerializedName("USD") var usd: CurrencyRemote
)
