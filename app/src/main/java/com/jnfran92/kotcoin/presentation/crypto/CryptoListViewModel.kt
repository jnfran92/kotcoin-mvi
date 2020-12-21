package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.uistate.CryptoListUIState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var getCryptoListUseCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    private val compositeDisposable = CompositeDisposable()

    val tx: MutableLiveData<CryptoListUIState> by lazy {
        MutableLiveData<CryptoListUIState>()
    }

    lateinit var publishSubject: PublishSubject<CryptoListIntent>
    lateinit var interpreter: Observable<CryptoListAction>
    lateinit var processor: CryptoListProcessor
    lateinit var reducer: Observable<CryptoListUIState>


    init {
        val applicationComponent = (application as KotcoinApp).applicationComponent
        applicationComponent.inject(this)



        publishSubject = PublishSubject.create()
        interpreter = publishSubject.flatMap {
            Observable.create<CryptoListAction> { emitter ->
                Timber.d("interpreter: intent $it")
                when(it){
                    CryptoListIntent.GetCryptoListIntent -> {
                        Timber.d("interpreter: CryptoListIntent.getCryptoListIntent: ")
                        emitter.onNext(CryptoListAction.GetCryptoList)
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


        processor = CryptoListProcessor(getCryptoListUseCase, mapper)


//        val processor = interpreter.map {
//            when(it){
//                CryptoListAction.getCryptoList -> {
//                    getCryptoListUseCase.useCase.blockingGet()
//                }
//                is CryptoListAction.getCryptoItemDetails -> {
//
//                }
//            }
//        }


        reducer = processor.tx.flatMap { result ->
            Observable.create<CryptoListUIState> { emitter ->
                Timber.d("reducer: $result")
                when(result){
                    CryptoListResult.GetCryptoListResult.InProgress -> { emitter.onNext(CryptoListUIState.ShowLoadingView)}
                    is CryptoListResult.GetCryptoListResult.OnError -> {emitter.onNext(CryptoListUIState.ShowErrorRetryView)}
                    is CryptoListResult.GetCryptoListResult.OnSuccess -> {emitter.onNext(CryptoListUIState.ShowDataView(result.data))}
                }
            }
        }



        this.compositeDisposable += interpreter
            .subscribeWith(processor.rx)

        this.compositeDisposable += reducer.subscribe(tx::postValue){}
    }

    fun processIntent(intent: CryptoListIntent){
        publishSubject.onNext(intent)
    }

//    fun observeUIStates(observer: DisposableObserver<CryptoListUIState>){
//        this.compositeDisposable += reducer
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(observer)
//    }


    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.dispose()
        this.getCryptoListUseCase.dispose()
    }
}
