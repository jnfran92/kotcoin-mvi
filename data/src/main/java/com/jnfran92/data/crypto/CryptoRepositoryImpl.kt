package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoDetailsToDomainMapper
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import com.jnfran92.domain.crypto.repository.CryptoRepository
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleDefer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Business Logic for managing [CryptoRemote] data.
 */
@Singleton
class CryptoRepositoryImpl @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    private val cryptoToDomainMapper: CryptoToDomainMapper,
    private val cryptoDetailsToDomainMapper: CryptoDetailsToDomainMapper
) : CryptoRepository {


    /**
     * Always get Crypto data(List) from the Cloud.
     */
    override fun getCryptoList(): Single<List<DomainCrypto>> {
        Timber.d("getCryptoList: ")
        val remoteDataSource = this.cryptoDataSourceFactory.createRemoteDataSource()
        val localDataSource = this.cryptoDataSourceFactory.createLocalDataSource()

        return remoteDataSource.getCryptoList()
            .flatMapCompletable {
                Timber.d("getCryptoList: flatMapCompletable saving remote  crypto ${it.size}")
                localDataSource.saveCryptoList(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
            }
            .andThen(SingleDefer {
                Timber.d("getCryptoList: and Then get data from local!")
                localDataSource.getCryptoList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
            })
            .map(cryptoToDomainMapper::transform)
    }

    override fun getCryptoById(cryptoId: Long): Single<DomainCryptoDetails> {
        Timber.d("getCryptoById $cryptoId")
        val localDataSource = this.cryptoDataSourceFactory.createLocalDataSource()
        return localDataSource.getCryptoById(cryptoId).map(cryptoDetailsToDomainMapper::transform)
    }
}