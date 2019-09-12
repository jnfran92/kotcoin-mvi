package com.jnfran92.model

import android.content.Context
import com.jnfran92.model.datasource.CryptoDataSourceFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoModelImp(
    @Inject private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    @Inject private val context: Context) {



}