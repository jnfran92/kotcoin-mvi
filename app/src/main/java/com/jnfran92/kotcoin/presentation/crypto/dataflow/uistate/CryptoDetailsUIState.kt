package com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate

import com.jnfran92.kotcoin.presentation.crypto.model.UICryptoDetails

sealed class CryptoDetailsUIState {

    object ShowDefaultView : CryptoDetailsUIState()

    object ShowLoadingView : CryptoDetailsUIState()

    class ShowErrorRetryView(val t: Throwable) : CryptoDetailsUIState()

    class ShowDataView(val data: UICryptoDetails) : CryptoDetailsUIState()
}
