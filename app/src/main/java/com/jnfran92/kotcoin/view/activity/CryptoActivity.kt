package com.jnfran92.kotcoin.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.di.component.CryptoComponent
import com.jnfran92.kotcoin.di.component.DaggerCryptoComponent
import com.jnfran92.kotcoin.view.fragment.CryptoListFragment
import kotlinx.android.synthetic.main.activity_crypto.*
import timber.log.Timber

/**
 * View for display a list of [Crypto] objects.
 */
class CryptoActivity : BaseActivity() {

    lateinit var cryptoListFragment: CryptoListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)
        setSupportActionBar(toolbar)
        Timber.d("onCreate")

        // Fragments
        initView()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }


    fun getCryptoComponent(): CryptoComponent{
        return DaggerCryptoComponent
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
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commit()
    }

    fun updateFragment(containerId:Int, fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commit()
    }


}
