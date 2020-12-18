package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.uistate.CryptoListUIState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var getCryptoListUseCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

//    @Inject lateinit var interpreter: CryptoListInterpreter

//
    private val compositeDisposable = CompositeDisposable()
//
//
//    private val interpreter: PublishSubject<CryptoListIntent> = PublishSubject.create()
//
//
////    private fun CryptoListIntent.toAction(): CryptoListAction{
////        return when(this){
////            CryptoListIntent.getCryptoListIntent -> {CryptoListAction.getCryptoList}
////            is CryptoListIntent.reloadCryptoList -> {CryptoListAction.noAvailableAction}
////            is CryptoListIntent.updateCryptoItemIntent -> {CryptoListAction.noAvailableAction}
////            is CryptoListIntent.getCryptoItemDetailsIntent -> {CryptoListAction.getCryptoItemDetails(itemId)}
////        }
////    }
//
//    private fun CryptoListResult.toUIState() {
//        Timber.d("toUIState")
//        return when(this){
//            else -> {}
//        }
//    }
//
//
//
//    init {
//        val applicationComponent = (application as KotcoinApp).applicationComponent
//        applicationComponent.inject(this)
//
//
////        val interpreterMapped = interpreter



    private val publishSubject: PublishSubject<CryptoListIntent> = PublishSubject.create()
    private val interpreter = publishSubject.flatMap {
        Observable.create<CryptoListAction> { emitter ->
            when(it){
                CryptoListIntent.getCryptoListIntent -> {emitter.onNext(CryptoListAction.getCryptoList)}
                is CryptoListIntent.reloadCryptoList -> {}
                is CryptoListIntent.updateCryptoItemIntent -> {}
                is CryptoListIntent.getCryptoItemDetailsIntent -> {}
            }
        }
    }

    private val processor = CryptoListProcessor(getCryptoListUseCase, mapper)

    private val reducer = processor.tx.flatMap { result ->
        Observable.create<CryptoListUIState> { emitter ->
            when(result){
                CryptoListResult.GetCryptoListResult.InProgress -> { emitter.onNext(CryptoListUIState.ShowLoadingView)}
                is CryptoListResult.GetCryptoListResult.OnError -> {emitter.onNext(CryptoListUIState.ShowLoadingView)}
                is CryptoListResult.GetCryptoListResult.OnSuccess -> {emitter.onNext(CryptoListUIState.ShowLoadingView)}
            }
        }
    }

    init {
        this.compositeDisposable += interpreter.subscribeWith(processor.rx)
    }

    fun processIntent(intent: CryptoListIntent){
        publishSubject.onNext(intent)
    }

    fun observeUIStates(observer: DisposableObserver<CryptoListUIState>){
        this.compositeDisposable += reducer.subscribeWith(observer)
    }

//
////        val publisherR: PublishSubject<CryptoListResult> = PublishSubject.create()
////        val reducer = publisherR.map { it.toUIState() }
//
//
////        val processor = interpreter.flatMap {
////            Observable<CryptoListAction>.create{}
////        }
//
//
//        val processor = interpreter.flatMap {action ->
//            Observable.create<CryptoListResult>{ emitter ->
//                when(action){
//                    CryptoListAction.getCryptoList -> {
//                        getCryptoListUseCase
//                            .useCase
//                            .toObservable()
//                            .startWith{ emitter.onNext(CryptoListResult.GetCryptoListResult.InProgress) }
//                            .doOnError { emitter.onNext(CryptoListResult.GetCryptoListResult.OnError(it)) }
//                            .doOnNext { emitter.onNext(CryptoListResult.GetCryptoListResult.OnSuccess(with(mapper){it.toUIModel()})) }
//                            .blockingFirst()
//                    }
//                    CryptoListAction.noAvailableAction -> {
//
//                    }
//                    is CryptoListAction.getCryptoItemDetails -> {
//
//                    }
//                }
//            }
//        }
//
//
//        val a = interpreter.flatMap { action->
//            when(action){
//                CryptoListAction.getCryptoList -> {
//                    getCryptoListUseCase
//                        .useCase
//                        .toObservable()
//                        .map { CryptoListResult.GetCryptoListResult.OnSuccess(with(mapper){it.toUIModel()}) }
//                        .cast(CryptoListResult::class.java)
//                }
//                CryptoListAction.noAvailableAction -> TODO()
//                is CryptoListAction.getCryptoItemDetails -> TODO()
//            }
//        }
//
//        a
//
//
//        val reducer = processor.flatMap { result ->
//            Observable.create<CryptoListUIState> { emitter->
//                when(result){
//                    CryptoListResult.GetCryptoListResult.InProgress -> {
//                        emitter.onNext(CryptoListUIState.ShowDefaultView)
//                        emitter.onNext(CryptoListUIState.ShowLoadingView)
//                    }
//                    is CryptoListResult.GetCryptoListResult.OnError -> {
//                        emitter.onNext(CryptoListUIState.ShowErrorRetryView)
//                        emitter.onNext(CryptoListUIState.HideLoadingView)
//                    }
//                    is CryptoListResult.GetCryptoListResult.OnSuccess -> {
//                        emitter.onNext(CryptoListUIState.ShowDataView(result.data))
//                        emitter.onNext(CryptoListUIState.HideLoadingView)
//                    }
//                }
//            }
//        }
//    }
//
//
////    val cryptoList: MutableLiveData<List<UICrypto>> by lazy{
////        MutableLiveData<List<UICrypto>>().also { loadCryptoList() }
////    }
//
//    /**
//     * Tx: Transmit UI Events
//     */
//    val tx: MutableLiveData<List<CryptoListUIState>> by lazy {
//        MutableLiveData<List<CryptoListUIState>>()
//    }
//
//
//    /**
//     * Rx: Receive intents
//     */
//    fun rx(intent: CryptoListIntent){
//        Timber.d("sendIntent: $intent")
//        this.interpreter.processIntent(intent)
//    }
//
//
//    private fun getCryptoList(){
//        Timber.d("getCryptoList: ")
//        tx.postValue(listOf(
//            CryptoListUIState.ShowDefaultView,
//            CryptoListUIState.HideLoadingView,
//            CryptoListUIState.HideErrorRetryView,
//            CryptoListUIState.HideLoadingView,
//            CryptoListUIState.HideDataView,
//            CryptoListUIState.ShowLoadingView
//        ))
//
//        this.getCryptoListUseCase.execute(object : DisposableSingleObserver<List<DomainCrypto>>() {
//            override fun onSuccess(t: List<DomainCrypto>) {
//                Timber.d("onSuccess $t")
//                tx.postValue(listOf(
//                    CryptoListUIState.HideDefaultView,
//                    CryptoListUIState.HideLoadingView,
//                    CryptoListUIState.HideErrorRetryView,
//                    CryptoListUIState.HideLoadingView,
//                    CryptoListUIState.ShowDataView(t.map { with(mapper){it.toUIModel()} }),
//                    CryptoListUIState.HideLoadingView
//                ))
//            }
//
//            override fun onError(e: Throwable) {
//                Timber.d("onError")
//                tx.postValue(listOf(
//                    CryptoListUIState.HideDefaultView,
//                    CryptoListUIState.HideLoadingView,
//                    CryptoListUIState.ShowErrorRetryView,
//                    CryptoListUIState.HideLoadingView,
//                    CryptoListUIState.HideDataView,
//                    CryptoListUIState.HideLoadingView
//                ))
//            }
//        })
//    }


    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.dispose()
        this.getCryptoListUseCase.dispose()
    }
}
