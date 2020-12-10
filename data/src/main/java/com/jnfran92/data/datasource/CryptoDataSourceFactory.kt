package com.jnfran92.data.datasource

import android.content.Context
import com.jnfran92.data.datasource.crypto.CacheCryptoDataSource
import com.jnfran92.data.datasource.crypto.CloudCryptoDataSource
import com.jnfran92.data.datasource.crypto.CryptoDataSource
import com.jnfran92.data.supplier.cache.CryptoCache
import com.jnfran92.data.supplier.cloud.CryptoApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory object to manage data sources for Crypto entities
 */
@Singleton
class CryptoDataSourceFactory @Inject constructor(
    private val context: Context,
    private val cryptoApi: CryptoApi,
    private val cryptoCache: CryptoCache){

    /**
     * Create a Data source for retrieving data from the Could REST API
     */
    fun createCloudDataSource(): CryptoDataSource{
        return CloudCryptoDataSource(this.cryptoApi)
    }

    /**
     * Create a Data source for retrieving data from the Cache's Device
     */
    fun createCacheDataSource(): CryptoDataSource{
        return CacheCryptoDataSource(this.context, this.cryptoCache)
    }

}