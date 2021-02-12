package com.jnfran92.kotcoin.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jnfran92.domain.crypto.GetCryptoListUseCase
import timber.log.Timber

class GetLatestCryptoDataWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val useCase: GetCryptoListUseCase
) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Timber.d("doWork")
        return try {
            val updatedItem = useCase().blockingGet()
            Timber.d("doWork: updated item $updatedItem")
            Result.success()
        }catch (e: Exception){
            Timber.d("doWork: failure $e")
            Result.failure()
        }
    }
}