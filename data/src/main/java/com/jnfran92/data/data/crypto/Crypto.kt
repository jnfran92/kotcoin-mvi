package com.jnfran92.data.data.crypto

import com.google.gson.annotations.SerializedName

/**
 * Crypto-currency data
 */
class Crypto(
    @SerializedName("id") var cryptoId:Int,
    @SerializedName("name") var name:String,
    @SerializedName("symbol") var symbol:String,
    @SerializedName("slug") var slug:String,
    @SerializedName("quote") var quoteEntity: Quote
    )