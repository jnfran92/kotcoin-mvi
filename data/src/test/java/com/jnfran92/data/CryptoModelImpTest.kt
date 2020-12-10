package com.jnfran92.data

import com.jnfran92.data.datasource.CryptoDataSourceFactory
import com.jnfran92.data.datasource.crypto.CloudCryptoDataSource
import com.jnfran92.data.datasource.crypto.CryptoDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.mock

class CryptoModelImpTest {

    private lateinit var cryptoModel: CryptoModel

    // Mocks
    private lateinit var mockCryptoDataSourceFactory: CryptoDataSourceFactory
    private lateinit var mockCryptoDataSource: CryptoDataSource

    @Before
    fun setUp() {
        mockCryptoDataSourceFactory = mock(CryptoDataSourceFactory::class.java)
        mockCryptoDataSource = mock(CloudCryptoDataSource::class.java)

        cryptoModel = CryptoModelImp(mockCryptoDataSourceFactory)

        given(mockCryptoDataSourceFactory.createCloudDataSource()).willReturn(mockCryptoDataSource)
    }

    @Test
    fun getCryptoListAlwaysFromCloud() {
        cryptoModel.getCryptoList()
        verify(mockCryptoDataSourceFactory).createCloudDataSource()
    }
}