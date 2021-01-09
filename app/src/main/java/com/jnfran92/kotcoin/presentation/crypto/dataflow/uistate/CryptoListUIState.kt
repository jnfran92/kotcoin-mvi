package com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoListUIState{

    object ShowDefaultView: CryptoListUIState()

    object ShowLoadingView: CryptoListUIState()

    class ShowErrorRetryView(val t: Throwable): CryptoListUIState()

    class ShowDataView(val data: List<UICrypto>): CryptoListUIState()
}
