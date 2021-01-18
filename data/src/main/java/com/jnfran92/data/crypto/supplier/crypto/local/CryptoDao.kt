package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.UsdPrice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptos")
    fun getAllCrypto(): Single<List<CryptoLocal>>

    @Query("SELECT * FROM usd_prices")
    fun getAllUsdPrice(): Single<List<UsdPrice>>

    @Insert
    fun addCrypto(crypto: CryptoLocal): Long

    @Insert
    fun addCryptoList(crypto: List<CryptoLocal>): List<Long>

    @Insert
    fun addUsdPrice(usdPrice: UsdPrice): Long

    @Insert
    fun addUsdPriceList(usdPriceList: List<UsdPrice>): List<Long>
}