package com.jnfran92.data.crypto.model.crypto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Crypto-currency data
 */
@Entity(tableName = "cryptos")
data class CryptoLocal(

    @PrimaryKey val cryptoId: Long,

    @ColumnInfo(name = "name") var name: String,

    @ColumnInfo(name = "symbol") var symbol: String,

    @ColumnInfo(name = "slug") var slug: String
)