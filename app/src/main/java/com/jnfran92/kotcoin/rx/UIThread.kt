package com.jnfran92.kotcoin.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Returns the UI thread for reactive programming.
 */
class UIThread @Inject constructor(): ObserverThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}