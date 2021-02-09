package com.jnfran92.kotcoin.presentation.crypto.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class UICryptoDetails(
    val cryptoId: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val price: List<Double>,
) : Parcelable