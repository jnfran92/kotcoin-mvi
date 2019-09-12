package com.jnfran92.model.entity

import com.google.gson.annotations.SerializedName

class UsdEntity (
    @SerializedName("price") var price:Double,
    @SerializedName("market_cap") var marketCap:Double,
    @SerializedName("last_updated") var lastUpdated:String
)
