package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptos")
    fun getAll(): List<CryptoLocal>

    @Insert
    fun add(vararg crypto: CryptoLocal)
}