package com.jnfran92.data.crypto.model.crypto

import androidx.room.Embedded
import androidx.room.Relation

data class CryptoWithHistoricUsdPrices(
    @Embedded val crypto: Crypto,
    @Relation(
        parentColumn = "cryptoId",
        entityColumn = "usdPriceId"
    )
    val historicUsdPrices: List<UsdPrice>
)