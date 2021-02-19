package com.jnfran92.kotcoin.presentation.crypto

import com.jnfran92.domain.crypto.GetCryptoListUseCase
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainPrice
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.dataflow.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.dataflow.reducer.CryptoListReducer
import com.jnfran92.kotcoin.presentation.crypto.dataflow.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class CryptoListViewModelTest {


    private val useCaseMock = mock<GetCryptoListUseCase>()
    private val toUIMapper = DomainCryptoToUIMapper()

    private val processor = CryptoListProcessor(useCaseMock, toUIMapper)
    private val interpreter = CryptoListInterpreter()
    private val reducer = CryptoListReducer()


    val viewModel = CryptoListViewModel(
        processor,
        interpreter,
        reducer
    )

    lateinit var testObserver: TestObserver<CryptoListUIState>

    @Before
    fun setup(){
        val dataFlow = interpreter flowTo processor flowTo reducer flowOn Schedulers.io()
        testObserver = dataFlow.test()
    }

    @Test
    fun `test normal case`(){
        val fakeDataList = listOf(
            DomainCrypto(
                id = 0L,
                name= "whatever",
                symbol= "whatever",
                slug= "whatever",
                tags= listOf( "whatever"),
                cmcRank= -1,
                circulatingSupply= 0.0,
                totalSupply= 0.0,
                maxSupply= 0.0,
                usdPrice= DomainPrice(
                    price = 0.0,
                    marketCap = 0.0,
                    volume24h = 0.0,
                    percentChange1h = 0.0,
                    percentChange7d = 0.0,
                    percentChange24h = 0.0,
                    lastUpdated = "whatever"
                ),
            )
        )
        println(fakeDataList)

        whenever(useCaseMock.invoke()).thenReturn(Single.just(fakeDataList))

        interpreter.processIntent(CryptoListIntent.GetCryptoListIntent)
        interpreter.processIntent(CryptoListIntent.GetCryptoListIntent)
        interpreter.processIntent(CryptoListIntent.GetCryptoListIntent)
        interpreter.processIntent(CryptoListIntent.GetCryptoListIntent)
        interpreter.processIntent(CryptoListIntent.GetCryptoListIntent)

        val values = testObserver.values()
        println(values)

//        testObserver.assertValueAt(0){it is CryptoListUIState.ShowDefaultView}
//        testObserver.assertValueAt(1){it is CryptoListUIState.ShowDefaultView}
//        testObserver.assertValueAt(2){it is CryptoListUIState.ShowDataView}
    }


    /**
     * infix helper: interpreter to processor
     */
    private infix fun CryptoListInterpreter.flowTo(processor: CryptoListProcessor):
            Observable<CryptoListResult> {
        return this.toObservable().compose(processor)
    }

    /**
     * infix helper: processor to reducer
     */
    private infix fun Observable<CryptoListResult>.flowTo(reducer: CryptoListReducer):
            Observable<CryptoListUIState> {
        return this.scan(CryptoListUIState.ShowDefaultView, reducer)
    }

    /**
     * infix helper: processor to reducer
     */
    private infix fun Observable<CryptoListUIState>.flowOn(scheduler: Scheduler):
            Observable<CryptoListUIState> {
        return this.observeOn(scheduler).subscribeOn(scheduler)
    }
}