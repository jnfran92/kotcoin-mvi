package com.jnfran92.kotcoin.presentation.crypto.controller

import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.kotcoin.ui.ViewListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base class for all view controllers. Note that  a [ViewListener] is needed to work.
 */
abstract class BaseController<T>(private val observerThread: ObserverThread,
                              private val subscriberExecutor: SubscriberExecutor) {


    lateinit var viewListener: ViewListener<T>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /*
    Life cycle of the Controller
     */
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume method.
     */
    abstract fun onResume()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's onPause().
     */
    abstract fun onPause()

    /**
     * Method tha control de lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    abstract fun onDestroy()


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