package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.Crypto

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto")
    fun getAll(): List<Crypto>

    @Insert
    fun add(vararg crypto: Crypto)
}