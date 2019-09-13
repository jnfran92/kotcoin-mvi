package com.jnfran92.kotcoin.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread: ObserverThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}