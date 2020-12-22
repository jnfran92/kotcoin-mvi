package com.jnfran92.kotcoin.presentation.crypto.dataflow.reducer

import com.jnfran92.kotcoin.presentation.crypto.dataflow.result.CryptoDetailsResult
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoDetailsUIState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.functions.BiFunction
import timber.log.Timber
import javax.inject.Inject

@ActivityRetainedScoped
class CryptoDetailsReducer @Inject constructor(): BiFunction<CryptoDetailsUIState, CryptoDetailsResult, CryptoDetailsUIState>{

    override fun apply(t1: CryptoDetailsUIState, t2: CryptoDetailsResult): CryptoDetailsUIState {
        Timber.d("apply")
        return resolveNextState(t1, t2)
    }

    private fun resolveNextState(previousState: CryptoDetailsUIState, currentResult: CryptoDetailsResult): CryptoDetailsUIState {
        return when(previousState){
            is CryptoDetailsUIState.ShowDefaultView -> previousState resolveWith currentResult
            is CryptoDetailsUIState.ShowLoadingView -> previousState resolveWith currentResult
            is CryptoDetailsUIState.ShowErrorRetryView -> previousState resolveWith currentResult
            is CryptoDetailsUIState.ShowDataView -> previousState resolveWith currentResult
        }
    }

    private infix fun CryptoDetailsUIState.ShowDefaultView.resolveWith(result: CryptoDetailsResult): CryptoDetailsUIState {
        return when(result){
            CryptoDetailsResult.GetCryptoDetailsResult.InProgress -> CryptoDetailsUIState.ShowLoadingView
            is CryptoDetailsResult.GetCryptoDetailsResult.OnError -> throw Exception("invalid path")
            is CryptoDetailsResult.GetCryptoDetailsResult.OnSuccess -> throw Exception("invalid path")
        }
    }

    private infix fun CryptoDetailsUIState.ShowLoadingView.resolveWith(result: CryptoDetailsResult): CryptoDetailsUIState {
        return when(result){
            CryptoDetailsResult.GetCryptoDetailsResult.InProgress -> throw Exception("invalid path")
            is CryptoDetailsResult.GetCryptoDetailsResult.OnError -> CryptoDetailsUIState.ShowErrorRetryView
            is CryptoDetailsResult.GetCryptoDetailsResult.OnSuccess -> CryptoDetailsUIState.ShowDataView(result.data)
        }
    }

    private infix fun CryptoDetailsUIState.ShowErrorRetryView.resolveWith(result: CryptoDetailsResult): CryptoDetailsUIState {
        return when(result){
            CryptoDetailsResult.GetCryptoDetailsResult.InProgress -> throw Exception("invalid path")
            is CryptoDetailsResult.GetCryptoDetailsResult.OnError -> throw Exception("invalid path")
            is CryptoDetailsResult.GetCryptoDetailsResult.OnSuccess -> throw Exception("invalid path")
        }
    }

    private infix fun CryptoDetailsUIState.ShowDataView.resolveWith(result: CryptoDetailsResult): CryptoDetailsUIState {
        return when(result){
            CryptoDetailsResult.GetCryptoDetailsResult.InProgress -> CryptoDetailsUIState.ShowLoadingView
            is CryptoDetailsResult.GetCryptoDetailsResult.OnError -> throw Exception("invalid path")
            is CryptoDetailsResult.GetCryptoDetailsResult.OnSuccess -> throw Exception("invalid path")
        }
    }
}