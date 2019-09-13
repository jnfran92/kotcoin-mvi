package com.jnfran92.kotcoin.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.di.component.CryptoComponent
import com.jnfran92.kotcoin.di.component.DaggerCryptoComponent
import com.jnfran92.kotcoin.view.fragment.CryptoListFragment
import timber.log.Timber

class CryptoActivity : BaseActivity() {

    lateinit var cryptoComponent: CryptoComponent
    lateinit var cryptoListFragment: CryptoListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)

        Timber.d("onCreate")

        // Injection Stuff
        initCryptoComponent()

        initView()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    private fun initCryptoComponent(){
        this.cryptoComponent = DaggerCryptoComponent
            .builder()
            .activityModule(activityModule)
            .applicationComponent(applicationComponent)
            .build()
    }

    private fun initView(){
        cryptoListFragment = CryptoListFragment()
        this.addFragment(R.id.fy_cryptoActivity_container, cryptoListFragment)
    }

    private fun addFragment(containerId:Int, fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment)
        fragmentTransaction.commit()
    }

    fun updateFragment(containerId:Int, fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commit()
    }


}
