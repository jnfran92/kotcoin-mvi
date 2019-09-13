package com.jnfran92.kotcoin.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Returns the UI thread for reactive programming.
 */
class UIThread: ObserverThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}