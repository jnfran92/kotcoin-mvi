package com.jnfran92.data

import com.jnfran92.data.crypto.CryptoRepositoryImp
import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.datasource.crypto.CloudCryptoDataSource
import com.jnfran92.data.crypto.datasource.crypto.CryptoDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.mock

class CryptoRepositoryImpTest {

    private lateinit var cryptoRepository: CryptoRepository

    // Mocks
    private lateinit var mockCryptoDataSourceFactory: CryptoDataSourceFactory
    private lateinit var mockCryptoDataSource: CryptoDataSource

    @Before
    fun setUp() {
        mockCryptoDataSourceFactory = mock(CryptoDataSourceFactory::class.java)
        mockCryptoDataSource = mock(CloudCryptoDataSource::class.java)

        cryptoRepository = CryptoRepositoryImp(
            mockCryptoDataSourceFactory
        )

        given(mockCryptoDataSourceFactory.createCloudDataSource()).willReturn(mockCryptoDataSource)
    }

    @Test
    fun getCryptoListAlwaysFromCloud() {
        cryptoRepository.getCryptoList()
        verify(mockCryptoDataSourceFactory).createCloudDataSource()
    }
}