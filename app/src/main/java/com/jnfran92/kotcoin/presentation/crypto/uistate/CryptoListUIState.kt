package com.jnfran92.kotcoin.presentation.crypto.uistate

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoListUIState{

    object ShowDefaultView: CryptoListUIState()
//    object HideDefaultView: CryptoListUIState()

    object ShowLoadingView: CryptoListUIState()
    object HideLoadingView: CryptoListUIState()

    object ShowErrorRetryView: CryptoListUIState()
//    object HideErrorRetryView: CryptoListUIState()

    class ShowDataView(val data: List<UICrypto>): CryptoListUIState()
//    object HideDataView : CryptoListUIState()

    class OnShowToastMessage(val message: String): CryptoListUIState()
}
