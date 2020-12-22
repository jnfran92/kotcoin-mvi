package com.jnfran92.kotcoin.presentation.crypto.mvi.uistate

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoListUIState{

    object ShowDefaultView: CryptoListUIState()

    object ShowLoadingView: CryptoListUIState()

    object ShowErrorRetryView: CryptoListUIState()

    class ShowDataView(val data: List<UICrypto>): CryptoListUIState()
}
