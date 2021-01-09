package com.jnfran92.data.crypto.datasource

import android.content.Context
import com.jnfran92.data.crypto.datasource.crypto.CacheCryptoDataSource
import com.jnfran92.data.crypto.datasource.crypto.RemoteCryptoDataSource
import com.jnfran92.data.crypto.datasource.crypto.CryptoDataSource
import com.jnfran92.data.crypto.datasource.crypto.LocalCryptoDataSource
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCache
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemote
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory object to manage data sources for Crypto entities
 */
@Singleton
class CryptoDataSourceFactory @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cryptoRemote: CryptoRemote,
    private val cryptoCache: CryptoCache,
    private val cryptoDao: CryptoDao){

    /**
     * Create a Data source for retrieving data from the Could REST API
     */
    fun createRemoteDataSource(): CryptoDataSource{
        return RemoteCryptoDataSource(this.cryptoRemote)
    }

    /**
     * Create a Data source for retrieving data from the Cache's Device
     */
    fun createCacheDataSource(): CryptoDataSource{
        return CacheCryptoDataSource(this.context, this.cryptoCache)
    }

    /**
     * Create Local Data source, local DB
     */
    fun createLocalDataSource(): CryptoDataSource{
        return LocalCryptoDataSource(this.cryptoDao)
    }
}