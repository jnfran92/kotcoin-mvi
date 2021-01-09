package com.jnfran92.kotcoin.presentation.crypto.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UICrypto(
    val cryptoId:Long,
    val name:String,
    val symbol:String,
    val slug:String,
    val price:Double,
    val marketCap:Double,
    val lastUpdated:String
) : Parcelable