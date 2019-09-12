package com.jnfran92.model.entity

import com.google.gson.annotations.SerializedName

class CryptoEntity(
    @SerializedName("id") val crytoId:Int,
    @SerializedName("name") var name:String,
    @SerializedName("symbol") var symbol:String,
    @SerializedName("slug") var slug:String
    )

//class CrytoQuote(
//    @SerializedName("USD") val crytoId:Int,
//)
//
//class CrytoUsd(
//    @SerializedName("price") val price:Double
//)