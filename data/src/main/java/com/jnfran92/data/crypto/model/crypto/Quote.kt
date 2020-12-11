package com.jnfran92.data.crypto.model.crypto

import com.google.gson.annotations.SerializedName

/**
 * Auxiliary class for [Crypto]
 */
class Quote(
    @SerializedName("USD") var usd: Usd
)
