package com.jnfran92.kotcoin.presentation.crypto

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.uistate.CryptoListUIState
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
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
    lateinit var reducer: ObservableTransformer<CryptoListResult, CryptoListUIState>


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
                    CryptoListIntent.getCryptoListIntent -> {
                        Timber.d("interpreter: CryptoListIntent.getCryptoListIntent: ")
                        emitter.onNext(CryptoListAction.getCryptoList)
                    }
                    is CryptoListIntent.reloadCryptoList -> {
                        Timber.d("interpreter: CryptoListIntent.reloadCryptoList ")
                    }
                    is CryptoListIntent.updateCryptoItemIntent -> {
                        Timber.d("interpreter: CryptoListIntent.updateCryptoItemIntent ")
                    }
                    is CryptoListIntent.getCryptoItemDetailsIntent -> {
                        Timber.d("interpreter: CryptoListIntent.getCryptoItemDetailsIntent ")
                    }
                }
            }
        }


        processor = ObservableTransformer { actions ->
            actions.flatMap { action ->
                when(action){
                    CryptoListAction.getCryptoList -> {
                        useCaseOne
                            .map (domainCryptoToUIMapper::transform)
                            .toObservable()
                            .map(CryptoListResult.GetCryptoListResult::OnSuccess)
                            .cast(CryptoListResult::class.java)
                            .startWith(CryptoListResult.GetCryptoListResult.InProgress)
                            .onErrorReturn(CryptoListResult.GetCryptoListResult::OnError)
                    }
                    is CryptoListAction.getCryptoItemDetails -> {
                        useCaseTwo
                            .map (domainCryptoToUIMapper::transform)
                            .toObservable()
                            .map(CryptoListResult.GetCryptoItemResult::OnSuccess)
                            .cast(CryptoListResult::class.java)
                            .startWith(CryptoListResult.GetCryptoItemResult.InProgress)
                            .onErrorReturn(CryptoListResult.GetCryptoItemResult::OnError)
                    }
                }
            }
        }


        reducer = ObservableTransformer { results ->
            results.flatMap { result ->
                when(result){
                    CryptoListResult.GetCryptoListResult.InProgress -> TODO()
                    is CryptoListResult.GetCryptoListResult.OnError -> TODO()
                    is CryptoListResult.GetCryptoListResult.OnSuccess -> TODO()

                    CryptoListResult.GetCryptoItemResult.InProgress -> TODO()
                    is CryptoListResult.GetCryptoItemResult.OnError -> TODO()
                    is CryptoListResult.GetCryptoItemResult.OnSuccess -> TODO()
                }


                Observable.just(CryptoListUIState.ShowLoadingView)
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

        publishSubject.onNext(CryptoListIntent.getCryptoListIntent)
        publishSubject.onNext(CryptoListIntent.getCryptoItemDetailsIntent(-1))
        publishSubject.onNext(CryptoListIntent.reloadCryptoList)
        publishSubject.onNext(CryptoListIntent.updateCryptoItemIntent(-1))
    }


    @Test
    fun testProcessor(){
        println("testProcessor")




    }


    @After
    fun thisIsTheEnd(){
        println("thisIsTheEnd")
    }
}