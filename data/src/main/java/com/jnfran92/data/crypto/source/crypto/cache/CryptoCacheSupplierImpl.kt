package com.jnfran92.data.crypto.source.crypto.cache

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoCacheSupplierImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CryptoCacheSupplier