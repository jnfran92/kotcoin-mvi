package com.jnfran92.data.crypto.model.crypto.remote

import com.google.gson.annotations.SerializedName

/**
 * Crypto-currency data
 */
data class CryptoRemote(
    @SerializedName("id") val cryptoId: Long,

    @SerializedName("name") var name: String,

    @SerializedName("symbol") var symbol: String,

    @SerializedName("slug") var slug: String,

    @SerializedName("quote") var quoteRemoteEntity: QuoteRemote
    )