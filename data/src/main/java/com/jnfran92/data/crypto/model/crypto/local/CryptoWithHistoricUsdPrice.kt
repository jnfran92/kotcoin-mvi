package com.jnfran92.data.crypto.model.crypto.local

import androidx.room.Embedded
import androidx.room.Relation


data class CryptoWithHistoricUsdPrice(
    @Embedded val crypto: CryptoLocal,
    @Relation(
        parentColumn = "cryptoId",
        entityColumn = "usdPriceId"
    )
    val historicUsdPricesLocal: UsdPriceLocal
)