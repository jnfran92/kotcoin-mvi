package com.jnfran92.kotcoin.presentation.crypto.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class UIPrice(
    var price: Double,
    var marketCap: Double,
    var volume24h: Double,
    var percentChange1h: Double,
    var percentChange24h: Double,
    var percentChange7d: Double,
    var lastUpdated: String
) : Parcelable