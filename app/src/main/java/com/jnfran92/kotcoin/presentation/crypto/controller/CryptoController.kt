package com.jnfran92.kotcoin.presentation.crypto.controller

import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.data.CryptoModel
import com.jnfran92.data.data.crypto.Crypto
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Controller for the CryptoListView(Activity).
 */
class CryptoController @Inject constructor(private val cryptoModel: CryptoModel,
                                           private val observerThread: ObserverThread,
                                           private val subscriberExecutor: SubscriberExecutor):
    BaseController<Crypto>(observerThread, subscriberExecutor) {
    override fun onResume() {
        Timber.d("onResume")
    }

    override fun onPause() {
        Timber.d("onPause")
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
    }


    /**
     * Display a List of [Crypto] objects
     */
    fun displayCryptoList(){
        this.prepareViewForOperation()
        val observer = this.cryptoModel.getCryptoList()
        this.addDisposable(
                observer
                .subscribeOn(Schedulers.from( this.subscriberExecutor))
                .observeOn(this.observerThread.getScheduler())
                .subscribeWith(ListCryptoDisposable())
        )
    }

    /**
     * Display an specific Crypto object by its Id.  Note: not implemented yet
     */
    fun displayCryptoById(cryptoId:Int){
        this.prepareViewForOperation()
        val observer = this.cryptoModel.getCryptoById(cryptoId)
        this.addDisposable(
            observer
                .subscribeOn(Schedulers.from( this.subscriberExecutor))
                .observeOn(this.observerThread.getScheduler())
                .subscribeWith(CryptoDisposable())
        )
    }

    /**
     * Add a new Crypto object. Note: not implemented yet
     */
    fun addCrypto(crypto: Crypto){
        this.prepareViewForOperation()
        val observer = this.cryptoModel.addCrypto(crypto)
        this.addDisposable(
            observer
                .subscribeOn(Schedulers.from( this.subscriberExecutor))
                .observeOn(this.observerThread.getScheduler())
                .subscribeWith(VoidDisposable())
        )
    }

    /**
     *  Delete a Crypto object. Note: not implemented yet
     */
    fun deleteCrypto(crypto: Crypto){
        this.prepareViewForOperation()
        val observer = this.cryptoModel.deleteCrypto(crypto)
        this.addDisposable(
            observer
                .subscribeOn(Schedulers.from( this.subscriberExecutor))
                .observeOn(this.observerThread.getScheduler())
                .subscribeWith(VoidDisposable())
        )
    }

    /*
    And the list of methods goes on...
     */

    private  fun prepareViewForOperation(){
        viewListener.hideRetry()
        viewListener.showLoading()
    }

    /**
     * Disposable for retrieving a List of [Crypto] objects.
     */
    private inner class ListCryptoDisposable: DisposableObserver<List<Crypto>>(){
        override fun onComplete() {
            viewListener.hideLoading()
        }

        override fun onNext(t: List<Crypto>?) {
            viewListener.hideLoading()
            if (t !== null){
                viewListener.showDataList(t)
            }else{
                viewListener.showErrorMessage("No Data.")
                viewListener.showRetry()
            }
        }

        override fun onError(e: Throwable?) {
            viewListener.hideLoading()
            viewListener.showRetry()
            viewListener.showErrorMessage(e?.message ?: "Unknown Error Message")
        }
    }

    /**
     * Disposable for retrieving a [Crypto] object. Not implemented yet...
     */
    private inner class CryptoDisposable: DisposableObserver<Crypto>(){
        override fun onComplete() {
            throw NotImplementedError()
        }

        override fun onNext(t: Crypto?) {
            throw NotImplementedError()
        }

        override fun onError(e: Throwable?) {
            throw NotImplementedError()
        }

    }


    /**
     * Disposable for add, remove, update operations... Not implemented yet...
     */
    private inner class VoidDisposable: DisposableObserver<Void>(){
        override fun onComplete() {
            throw NotImplementedError()
        }

        override fun onNext(t: Void?) {
            throw NotImplementedError()
        }

        override fun onError(e: Throwable?) {
            throw NotImplementedError()
        }
    }
}