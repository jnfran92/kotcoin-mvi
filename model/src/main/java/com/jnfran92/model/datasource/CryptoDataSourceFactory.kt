package com.jnfran92.model.datasource

import android.content.Context
import com.jnfran92.model.supplier.cache.CryptoCache
import com.jnfran92.model.supplier.cloud.CryptoApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory object to manage data sources for Crypto entities
 */
@Singleton
class CryptoDataSourceFactory(
    @Inject private val context: Context,
    @Inject private val cryptoApi: CryptoApi,
    @Inject private val cryptoCache: CryptoCache){

    fun createCloudDataSource(){

    }

    fun createCacheDataSource(){

    }

}