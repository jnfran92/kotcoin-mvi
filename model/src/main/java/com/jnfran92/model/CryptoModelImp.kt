package com.jnfran92.model

import com.jnfran92.model.data.crypto.Crypto
import com.jnfran92.model.datasource.CryptoDataSourceFactory
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Business Logic for managing [Crypto] data.
 */
@Singleton
class CryptoModelImp @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory): CryptoModel {


    /**
     * This data fi a single crypto can be stored in cache, maybe..., but not implemented yet
     */
    override fun getCryptoById(cryptoId: Int): Observable<Crypto> {
        throw NotImplementedError("API just provides a list of crypto data.")
    }

    /**
     * Always get Crypto data(List) from the Cloud.
     */
    override fun getCryptoList(): Observable<List<Crypto>> {
        val cloudDataSource = this.cryptoDataSourceFactory.createCloudDataSource()
        return cloudDataSource.getCryptoList()
    }

    override fun addCrypto(crypto: Crypto): Observable<Void> {
        throw NotImplementedError("API just provides a list of crypto data.")
    }

    override fun editCrypto(crypto: Crypto): Observable<Void> {
        throw NotImplementedError("API just provides a list of crypto data.")
    }

    override fun deleteCrypto(crypto: Crypto): Observable<Void> {
        throw NotImplementedError("API just provides a list of crypto data.")
    }

    override fun updateCrypto(crypto: Crypto): Observable<Void> {
        throw NotImplementedError("API just provides a list of crypto data.")
    }
}