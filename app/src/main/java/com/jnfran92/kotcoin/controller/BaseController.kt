package com.jnfran92.kotcoin.controller

import android.database.Observable
import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.kotcoin.view.ViewListener
import com.jnfran92.model.CryptoModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

/**
 * Base class for all view controllers. Note that  a [ViewListener] is needed to work.
 */
abstract class BaseController<T>(private val observerThread: ObserverThread,
                              private val subscriberExecutor: SubscriberExecutor) {


    lateinit var viewListener: ViewListener<T>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Add a new Disposable Observer
     */
    fun addDisposable(disposable: Disposable){
        this.compositeDisposable.add(disposable)
    }

    /**
     * Release disposables
     */
    fun dispose(){
        this.compositeDisposable.dispose()
    }
}