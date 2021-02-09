package com.jnfran92.data.crypto.supplier.crypto.cache

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoCacheSupplierImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ):CryptoCacheSupplier {
}