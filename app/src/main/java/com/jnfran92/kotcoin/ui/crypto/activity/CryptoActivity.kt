package com.jnfran92.kotcoin.ui.crypto.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jnfran92.kotcoin.databinding.ActivityCryptoBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * View for display a list of [Crypto] objects.
 */
@AndroidEntryPoint
class CryptoActivity : AppCompatActivity(){

    /**
     * Data binding
     */
    lateinit var binding: ActivityCryptoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // action bar
        setSupportActionBar(binding.tbCryptoActivityBar.tbGenericViewBar)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }


//    fun getCryptoComponent(): CryptoComponent{
//        val appComponent = (application as KotcoinApp).applicationComponent
//        return DaggerCryptoComponent
//            .builder()
//            .activityModule(ActivityModule(this))
//            .applicationComponent(appComponent)
//            .build()
//    }
}
