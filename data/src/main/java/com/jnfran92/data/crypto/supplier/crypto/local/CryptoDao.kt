package com.jnfran92.data.crypto.supplier.crypto.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.CryptoWithHistoricUsdPrice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptos")
    fun getAll(): Single<List<CryptoLocal>>

    @Insert
    fun add(vararg crypto: CryptoLocal): Completable

    @Insert
    fun addList(crypto: List<CryptoLocal>): Completable

    @Transaction
    @Insert
    fun addCryptoWithHistoricUsdPrice(vararg item: CryptoWithHistoricUsdPrice): Completable

    @Transaction
    @Insert
    fun addCryptoListWithHistoricUsdPrice(itemList: List<CryptoWithHistoricUsdPrice>): Completable
}