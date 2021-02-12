package com.jnfran92.kotcoin.ui.crypto.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.*
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.databinding.ActivityCryptoBinding
import com.jnfran92.kotcoin.worker.GetLatestCryptoDataWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_crypto.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.math.log

/**
 * View for display a list of [Crypto] objects.
 */
@AndroidEntryPoint
class CryptoActivity : AppCompatActivity() {

    /**
     * Data binding
     */
    lateinit var binding: ActivityCryptoBinding

    /**
     * Bar Navigation
     */
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // action bar
        setSupportActionBar(binding.tbCryptoActivityBar.tbGenericViewBar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        startWorker()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    private fun startWorker() {
        Timber.d("startWorker: ")
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workerRequest = PeriodicWorkRequestBuilder<GetLatestCryptoDataWorker>(6, TimeUnit.HOURS)
            .setInitialDelay(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueueUniquePeriodicWork("getLastDataWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workerRequest)
    }
}
