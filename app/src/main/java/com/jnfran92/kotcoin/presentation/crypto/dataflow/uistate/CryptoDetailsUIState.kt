package com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoDetailsUIState{

    object ShowDefaultView: CryptoDetailsUIState()

    object ShowLoadingView: CryptoDetailsUIState()

    object ShowErrorRetryView: CryptoDetailsUIState()

    class ShowDataView(val data: UICrypto): CryptoDetailsUIState()
}
