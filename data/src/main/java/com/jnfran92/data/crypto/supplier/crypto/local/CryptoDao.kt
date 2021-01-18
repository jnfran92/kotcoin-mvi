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
    fun getAllCrypto(): Single<List<CryptoLocal>>

    @Query("SELECT * FROM usd_prices")
    fun getAllUsdPrice(): Single<List<UsdPrice>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCrypto(vararg crypto: CryptoLocal): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCryptoList(crypto: List<CryptoLocal>): Completable

    @Insert
    fun addUsdPrice(usdPrice: UsdPrice): Completable

    @Insert
    fun addUsdPriceList(usdPriceList: List<UsdPrice>): Completable
}