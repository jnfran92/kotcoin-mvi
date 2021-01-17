package com.jnfran92.kotcoin.presentation.crypto

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.kotcoin.presentation.crypto.dataflow.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import com.jnfran92.kotcoin.presentation.crypto.dataflow.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class CryptoListViewModelTest{


    /**
     * interpreter
     */
    lateinit var publishSubject: PublishSubject<CryptoListIntent>
    lateinit var interpreter: Observable<CryptoListAction>

    /**
     * processor
     */
    lateinit var processor: ObservableTransformer<CryptoListAction, CryptoListResult>

    /**
     * reducer
     */
//    lateinit var reducer: ObservableTransformer<CryptoListResult, CryptoListUIState>
    lateinit var reducer: BiFunction<CryptoListResult, CryptoListUIState, CryptoListUIState>


    val compositeDisposable = CompositeDisposable()
    val domainCryptoToUIMapper = DomainCryptoToUIMapper()

    @Before
    fun setup(){
        println("setup")

        // use cases
        val useCaseOne = Single.just(
            listOf(
                DomainCrypto(1,"1", "1", "1", 1.0, 1.0, "1"),
                DomainCrypto(2,"2", "2", "2", 2.0, 2.0, "2"),
                DomainCrypto(3,"3", "3", "3", 3.0, 3.0, "3")
            )
        )

        val useCaseTwo = Single.just(
            DomainCrypto(1,"1", "1", "1", 1.0, 1.0, "1"),
        )

        // interpreter
        publishSubject = PublishSubject.create()
        interpreter = publishSubject.flatMap {
            Observable.create<CryptoListAction> { emitter ->
                Timber.d("interpreter: intent $it")
                when(it){
                    CryptoListIntent.GetCryptoListIntent -> {
                        Timber.d("interpreter: CryptoListIntent.getCryptoListIntent: ")
                        emitter.onNext(CryptoListAction.GetCryptoList)
                    }
                }
            }
        }


        processor = ObservableTransformer { actions ->
            actions.flatMap { action ->
                when(action){
                    CryptoListAction.GetCryptoList -> {
                        useCaseOne
                            .map (domainCryptoToUIMapper::transform)
                            .toObservable()
                            .map(CryptoListResult.GetCryptoListResult::OnSuccess)
                            .cast(CryptoListResult::class.java)
                            .startWith(CryptoListResult.GetCryptoListResult.InProgress)
                            .onErrorReturn(CryptoListResult.GetCryptoListResult::OnError)
                    }
                }
            }
        }

    }

    @Test
    fun testInterpreter(){
        println("testInterpreter")

        compositeDisposable.add(
            interpreter.subscribe(
                {
                    println("interpreter: OnNext $it")
                },
                {
                    println("interpreter: OnError: $it")
                }
            )
        )

        publishSubject.onNext(CryptoListIntent.GetCryptoListIntent)
    }


    @Test
    fun testProcessor(){
        println("testProcessor")

        compositeDisposable.add(
            Observable.just<CryptoListAction>(
                CryptoListAction.GetCryptoList,
                CryptoListAction.GetCryptoList,)
                .compose(processor)
                .subscribe(
                    { println("processor: onSuccess $it")},
                    { println("processor: onError $it")},
                    { println("processor: onComplete")}
                )
        )
    }


    @Test
    fun testReducer(){
        println("testReducer")
        val fakeUICrypto = UICrypto(1, "1", "1", "1", 1.0, 1.0, "1")

        val reducer = BiFunction(::resolveNextState)

        Observable.just<CryptoListResult>(
            CryptoListResult.GetCryptoListResult.InProgress,
            CryptoListResult.GetCryptoListResult.OnSuccess(listOf(fakeUICrypto))
        ).scan(CryptoListUIState.ShowDefaultView, reducer)
            .subscribe(
                { println("onNext: $it")},
                { println("onError: $it")},
                { println("onComplete:")})

    }

    fun resolveNextState(previousState: CryptoListUIState, currentResult: CryptoListResult): CryptoListUIState{
        return when(previousState){
            is CryptoListUIState.ShowDefaultView -> previousState resolveWith currentResult
            is CryptoListUIState.ShowLoadingView -> previousState resolveWith currentResult
            is CryptoListUIState.ShowErrorRetryView -> previousState resolveWith currentResult
            is CryptoListUIState.ShowDataView -> previousState resolveWith currentResult
        }
    }

    infix fun CryptoListUIState.ShowDefaultView.resolveWith(result: CryptoListResult): CryptoListUIState{
        return when(result){
            CryptoListResult.GetCryptoListResult.InProgress -> CryptoListUIState.ShowLoadingView
            is CryptoListResult.GetCryptoListResult.OnError -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnSuccess -> throw Exception("invalid path")
        }
    }

    infix fun CryptoListUIState.ShowLoadingView.resolveWith(result: CryptoListResult): CryptoListUIState{
        return when(result){
            CryptoListResult.GetCryptoListResult.InProgress -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnError -> CryptoListUIState.ShowErrorRetryView(result.t)
            is CryptoListResult.GetCryptoListResult.OnSuccess -> CryptoListUIState.ShowDataView(result.data)
        }
    }

    infix fun CryptoListUIState.ShowErrorRetryView.resolveWith(result: CryptoListResult): CryptoListUIState{
        return when(result){
            CryptoListResult.GetCryptoListResult.InProgress -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnError -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnSuccess -> throw Exception("invalid path")
        }
    }

    infix fun CryptoListUIState.ShowDataView.resolveWith(result: CryptoListResult): CryptoListUIState{
        return when(result){
            CryptoListResult.GetCryptoListResult.InProgress -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnError -> throw Exception("invalid path")
            is CryptoListResult.GetCryptoListResult.OnSuccess -> throw Exception("invalid path")
        }
    }

    @After
    fun thisIsTheEnd(){
        println("thisIsTheEnd my only friend")
        compositeDisposable.dispose()
    }
}
