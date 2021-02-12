package com.jnfran92.kotcoin.presentation.crypto.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class UICrypto(
    val id: Long,
    var name: String,
    var symbol: String,
    var slug: String,
    var tags: List<String>,
    var cmcRank: Int,
    var circulatingSupply: Double,
    var totalSupply: Double,
    var maxSupply: Double,
    val usdPrice: UIPrice
) : Parcelable