package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import io.reactivex.Single
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
        val cloudDataSource = this.cryptoDataSourceFactory.createRemoteDataSource()
        val localDataSource = this.cryptoDataSourceFactory.createLocalDataSource()

//        val cloudResult = cloudDataSource.getCryptoList().doOnSuccess {
//            Timber.d("getCryptoList: Saving Crypto in Local")
//            it.forEach { crypto ->
//                val id = localDataSource.saveCrypto(crypto).subscribeOn(Schedulers.io()).subscribe({ Timber.d("getCryptoList: saving onComplete") },
//                    { Timber.d("getCryptoList: saving on Error $it") })
//                Timber.d("getCryptoList: object saved $id")
//            }
//        }

        val data = cloudDataSource.getCryptoList().doOnSuccess {
            Timber.d("getCryptoList: cloud request successful: requested ${it.size} items")
            localDataSource.saveCryptoList(it)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io()) }

            .doOnError { Timber.d("getCryptoList: error on saving data in local : $it") }
            .flatMap {
                Timber.d("getCryptoList: getting from Local")
                localDataSource.getCryptoList()
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
            }

//        val value = cloudDataSource.getCryptoList().flatMap {
//            localDataSource.saveCryptoList(it)
//            localDataSource.getCryptoList()
//                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//        }


        return data.map(mapper::transform)
    }
}