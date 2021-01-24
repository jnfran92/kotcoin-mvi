package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Business Logic for managing [Crypto] data.
 */
@Singleton
class CryptoRepositoryImp @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    private val mapper: CryptoToDomainMapper): CryptoRepository {


    /**
     * Always get Crypto data(List) from the Cloud.
     */
    override fun getCryptoList(): Single<List<DomainCrypto>> {
        Timber.d("getCryptoList: ")
        val remoteDataSource = this.cryptoDataSourceFactory.createRemoteDataSource()
        val localDataSource = this.cryptoDataSourceFactory.createLocalDataSource()

        return remoteDataSource.getCryptoList()
            .flatMapCompletable {
                localDataSource.saveCryptoList(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
            }
            .andThen ( SingleSource<List<Crypto>> {
                localDataSource.getCryptoList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io()) })
            .map(mapper::transform)
    }
}