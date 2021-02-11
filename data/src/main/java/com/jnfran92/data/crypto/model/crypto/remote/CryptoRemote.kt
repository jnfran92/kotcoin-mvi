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
    @SerializedName("quote") var quoteRemoteEntity: QuoteRemote,
    @SerializedName("tags") var tags: List<String>,
    @SerializedName("cmc_rank") var cmcRank: Int,
    @SerializedName("circulating_supply") var circulatingSupply: Int,
    @SerializedName("total_supply") var totalSupply: Int,
    @SerializedName("max_supply") var maxSupply: Int
)