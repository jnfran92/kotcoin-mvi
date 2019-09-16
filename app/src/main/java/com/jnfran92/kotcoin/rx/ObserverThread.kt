package com.jnfran92.kotcoin.rx

import io.reactivex.Scheduler

/**
 * Interface representing the main/UI Thread. It is the observer thread for reactive programming.
 */
interface ObserverThread {
    fun getScheduler(): Scheduler
}