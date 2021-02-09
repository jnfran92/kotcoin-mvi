package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.UsdPriceLocal

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


    @Query("SELECT * FROM usd_prices")
    fun getAllUsdPrice(): List<UsdPriceLocal>

    @Query("SELECT * FROM usd_prices WHERE cryptoLocalId = :cryptoId")
    fun getUsdPricesByCryptoId(cryptoId: Long): List<UsdPriceLocal>

    @Insert
    fun addUsdPrice(usdPriceLocal: UsdPriceLocal): Long

    @Insert
    fun addUsdPriceList(usdPriceLocalList: List<UsdPriceLocal>)
}