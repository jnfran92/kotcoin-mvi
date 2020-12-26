package com.jnfran92.data.crypto.model.crypto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Crypto-currency data
 */
@Entity
data class Crypto(
    @PrimaryKey
    @SerializedName("id") val cryptoId: Long,
    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String,
    @ColumnInfo(name = "symbol")
    @SerializedName("symbol") var symbol: String,
    @ColumnInfo(name = "slug")
    @SerializedName("slug") var slug: String,
    @SerializedName("quote") var quoteEntity: Quote
    )