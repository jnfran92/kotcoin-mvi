package com.jnfran92.kotcoin.rx

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Decorated with [ThreadPoolExecutor].
 */
@Singleton
class JobExecutor @Inject constructor():SubscriberExecutor{
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(3,
        5, 10, TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory())

    override fun execute(p0: Runnable?) {
        this.threadPoolExecutor.execute(p0)
    }
}