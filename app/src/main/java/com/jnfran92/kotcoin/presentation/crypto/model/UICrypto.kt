package com.jnfran92.kotcoin.presentation.crypto.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.jnfran92.data.crypto.model.crypto.Price
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
    var circulatingSupply: Int,
    var totalSupply: Int,
    var maxSupply: Int,
    val usdPrice: UIPrice
) : Parcelable