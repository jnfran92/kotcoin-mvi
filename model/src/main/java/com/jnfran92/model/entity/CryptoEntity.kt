package com.jnfran92.model.entity

import com.google.gson.annotations.SerializedName

class CryptoEntity(
    @SerializedName("id") var cryptoId:Int,
    @SerializedName("name") var name:String,
    @SerializedName("symbol") var symbol:String,
    @SerializedName("slug") var slug:String,
    @SerializedName("quote") var quoteEntity: QuoteEntity
    )