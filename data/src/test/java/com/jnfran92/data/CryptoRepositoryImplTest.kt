package com.jnfran92.data

import com.jnfran92.data.crypto.CryptoRepositoryImpl
import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.datasource.crypto.RemoteCryptoDataSource
import com.jnfran92.data.crypto.datasource.crypto.CryptoDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.mock

class CryptoRepositoryImplTest {

    private lateinit var cryptoRepository: CryptoRepository

    // Mocks
    private lateinit var mockCryptoDataSourceFactory: CryptoDataSourceFactory
    private lateinit var mockCryptoDataSource: CryptoDataSource

    @Before
    fun setUp() {
        mockCryptoDataSourceFactory = mock(CryptoDataSourceFactory::class.java)
        mockCryptoDataSource = mock(RemoteCryptoDataSource::class.java)

        cryptoRepository = CryptoRepositoryImpl(
            mockCryptoDataSourceFactory
        )

        given(mockCryptoDataSourceFactory.createRemoteDataSource()).willReturn(mockCryptoDataSource)
    }

    @Test
    fun getCryptoListAlwaysFromCloud() {
        cryptoRepository.getCryptoList()
        verify(mockCryptoDataSourceFactory).createRemoteDataSource()
    }
}