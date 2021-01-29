package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.UsdPrice
import io.reactivex.Completable
import io.reactivex.Single

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
    fun getAllUsdPrice(): List<UsdPrice>

    @Query("SELECT * FROM usd_prices WHERE cryptoLocalId = :cryptoId")
    fun getUsdPricesByCryptoId(cryptoId: Long): List<UsdPrice>

    @Insert
    fun addUsdPrice(usdPrice: UsdPrice): Long

    @Insert
    fun addUsdPriceList(usdPriceList: List<UsdPrice>)
}