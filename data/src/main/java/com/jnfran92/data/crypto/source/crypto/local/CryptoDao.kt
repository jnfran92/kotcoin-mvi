package com.jnfran92.data.crypto.source.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.PriceLocal

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptos")
    fun getAllCrypto(): List<CryptoLocal>

    @Query("SELECT * FROM cryptos WHERE cryptoId = :cryptoId")
    fun getCryptoById(cryptoId: Long): CryptoLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCrypto(crypto: CryptoLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCryptoList(crypto: List<CryptoLocal>)


    @Query("SELECT * FROM prices")
    fun getAllUsdPrices(): List<PriceLocal>

    @Query("SELECT * FROM prices WHERE crypto_id = :cryptoId")
    fun getUsdPricesByCryptoId(cryptoId: Long): List<PriceLocal>

    @Insert
    fun addUsdPrice(priceLocal: PriceLocal): Long

    @Insert
    fun addUsdPriceList(priceLocalList: List<PriceLocal>)
}